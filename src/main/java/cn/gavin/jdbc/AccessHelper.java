/*
 * AccessHelper.java
 * Date: 12/21/2015
 * Time: 3:07 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin.jdbc;

import java.sql.*;

public class AccessHelper {
private Connection connection;

public AccessHelper(String file, String user, String password) {
	try {
		connection = DriverManager.getConnection("jdbc:ucanaccess:" + file, user, password);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public AccessHelper(String file) {
	try {
		connection = DriverManager.getConnection("jdbc:ucanaccess://" + file);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void close() {
	try {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public Connection getConnection() {
	return connection;
}

/**
 * 可以使用 table_name where 添加过滤条件
 * @param page
 * @param row
 * @param table
 * @return
 */
public ResultSet queryByPage(int page, int row, String table) {
	int end = page * row;
	String sql = String.format("select top %s * from (select top %s * from %s order by id desc) order by id", row, end, table);
	try {
		Statement statement = connection.createStatement();
		return statement.executeQuery(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public int getCount(String table) throws SQLException {
	String sql = "select count(*) from " + table;
	ResultSet rs = connection.createStatement().executeQuery(sql);
	if(rs.next()){
		return rs.getInt(1);
	}else{
		return 0;
	}
}

}

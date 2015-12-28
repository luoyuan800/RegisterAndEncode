/*
 * SqliteHelper.java
 * Date: 12/21/2015
 * Time: 11:15 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteHelper {
private Connection connection;
private String user;
private String password;
public SqliteHelper(String path, String user, String password) throws ClassNotFoundException {
	this.user = user;
	this.password = password;
	connection = getConnection(path);
}

public SqliteHelper(String path) throws ClassNotFoundException {
	connection = connection(path);
}

public void close(){
	try {
		if(connection!=null && !connection.isClosed()){
			connection.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public Connection connection(String path) throws ClassNotFoundException {
	try {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + path);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public Connection getConnection(String path) throws ClassNotFoundException {
	try {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + path, user, password);

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public Connection getConnection(){
	return connection;
}

public void beginTransaction() throws SQLException {
	connection.setAutoCommit(false);
}
public void stopTransaction() throws SQLException {
	connection.commit();
	connection.setAutoCommit(true);
}

public void insert(String sql){
	Statement statement = null;
	try {
		statement = connection.createStatement();
		System.out.println(sql);
		statement.execute(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

public void createTable(String targetTable) {
	insert(String.format("create table if not exists %s (" +
			"id NUMBER NOT NULL PRIMARY KEY, " +
			"cont_name TEXT, " +
			"phone TEXT, " +
			"call TEXT, " +
			"com_name TEXT, " +
			"produce TEXT, " +
			"address TEXT, " +
			"capital TEXT, " +
			"representative TEXT, " +
			"peo_num TEXT, " +
			"create_time TEXT)", targetTable));
}
}

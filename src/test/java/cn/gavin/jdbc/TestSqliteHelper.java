package cn.gavin.jdbc;/*
 * TestSqliteHelper.java
 * Date: 12/21/2015
 * Time: 11:38 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class TestSqliteHelper {

@Test
public void testConnection(){
//	SqliteHelper.connection();
}
@Test
public void testGetConnection() throws SQLException, ClassNotFoundException {
	SqliteHelper helper = new SqliteHelper("test.sb");
	Connection connection = helper.getConnection();
	assertFalse(connection.isClosed());
	helper.close();
}
}

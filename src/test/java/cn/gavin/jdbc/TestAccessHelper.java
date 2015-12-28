package cn.gavin.jdbc;/*
 * TestAccessHelper.java
 * Date: 12/21/2015
 * Time: 3:21 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class TestAccessHelper {
@Test
public void testConnectionOpen() throws SQLException {
	AccessHelper accessHelper = new AccessHelper("test.accdb");
	assertFalse(accessHelper.getConnection().isClosed());
	accessHelper.close();
}
}

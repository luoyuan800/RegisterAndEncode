/*
 * TestTransfer.java
 * Date: 12/27/2015
 * Time: 2:43 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/
package cn.gavin.action;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.UUID;

public class TestTransfer {

@Test
public void testTransfer() throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException,
		NoSuchPaddingException, IllegalBlockSizeException, SQLException, BadPaddingException, UnsupportedEncodingException {
	String key = "48, 92, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, 75, 0, 48, 72, 2, 65, 0, -31, -88," +
			" -116, -113, -111, 0, 119, 24, -128, 25, 43, 86, 21, 9, -55, 7, -5, 123, -59, -61, 110, -113, 64, 12, 4, " +
			"43, -95, 38, 90, 124, 24, -103, 58, -80, -45, 49, -55, 59, 73, 55, 117, 59, 111, 98, -99, -42, -19, -45, " +
			"-101, -12, 83, 46, -4, 60, 117, 48, 2, 53, 9, -57, -57, -118, -1, 51, 2, 3, 1, 0, 1";
	TransferToSqlite transferToSqlite = new TransferToSqlite("luo",key,"test", "test");
	transferToSqlite.open("L:/RegisterAndEncode/test.accdb");
	transferToSqlite.transfer(4);
	transferToSqlite.close();
}
}

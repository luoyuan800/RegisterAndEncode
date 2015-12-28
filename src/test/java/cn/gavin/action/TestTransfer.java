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
	String key = "48, 92, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, 75, 0, 48, 72, 2, 65, 0, -77, 43, " +
			"110, 73, 48, 23, -33, -5, 70, -4, 61, 52, 116, 10, 4, 6, -49, -5, 88, -106, 20, -2, 106, 40, 81, -19, -34, " +
			"120, -126, 85, -64, -103, 20, -15, 44, 20, -79, 80, -42, -66, 68, 28, -48, 55, 2, -65, -28, -74, -49, 87," +
			" 119, 115, 103, -126, -52, 124, 44, 93, 127, 101, 95, 64, -42, -95, 2, 3, 1, 0, 1";
	System.out.println("user = " + key);
	TransferToSqlite transferToSqlite = new TransferToSqlite("def",key,"test", "test");
	transferToSqlite.open("L:/RegisterAndEncode/test.accdb");
	transferToSqlite.transfer(4);
	transferToSqlite.close();
}
}

/*
 * TransferToSqlite.java
 * Date: 12/21/2015
 * Time: 5:45 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin.action;

import cn.gavin.PathUtils;
import cn.gavin.encryption.Encode;
import cn.gavin.jdbc.AccessHelper;
import cn.gavin.jdbc.SqliteHelper;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransferToSqlite {
private String name;
private String key;
private SqliteHelper sqlite;
private AccessHelper access;
private String sourceTable;
private String targetTable;
private String outputPath;
private Encode encode;

public TransferToSqlite(String name, String key, String sourceTable, String targetTable) {
	this.name = name;
	this.key = key;
	this.sourceTable = sourceTable;
	this.targetTable = targetTable;
}

public void open(String source) throws ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
	File sqlitFile = PathUtils.getResourcePath(name);
	if (source != null) {
		access = new AccessHelper(source);
	}
	if (sqlitFile != null) {
		outputPath = sqlitFile.getPath();
		sqlite = new SqliteHelper(sqlitFile.getPath());
		sqlite.createTable(targetTable);
		encode = new Encode(key);
	}
}

public boolean transfer(int page) throws SQLException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
	ResultSet resultSet = access.queryByPage(page, 20, sourceTable);
	String insert = "INSERT INTO " + targetTable + " values ('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s', '%s')";
	sqlite.beginTransaction();
	while (resultSet.next()) {
		String sql = String.format(insert,resultSet.getString(1),encode.encode(resultSet.getString(2)),encode.encode(resultSet.getString(3)),
				encode.encode(resultSet.getString(4)),encode.encode(resultSet.getString(5)),encode.encode(resultSet.getString(6)),encode.encode(resultSet.getString(7)),
				encode.encode(resultSet.getString(8)),encode.encode(resultSet.getString(9)), encode.encode(resultSet.getString(10)), encode.encode(resultSet.getString(11)));
		sqlite.insert(sql);
	}
	sqlite.stopTransaction();
	return false;
}

public int getTotalPage() {
	try {
		return access.getCount(sourceTable);
	} catch (SQLException e) {
		e.printStackTrace();
		return 0;
	}
}

public void close() {
	if(access!=null){
		access.close();
	}
	if(sqlite!=null){
		sqlite.close();
	}
}

public String getPath() {
	return outputPath;
}
}

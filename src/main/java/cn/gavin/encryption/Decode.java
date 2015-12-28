/*
 * Decode.java
 * Date: 12/21/2015
 * Time: 5:01 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin.encryption;

import sun.security.rsa.RSAPrivateCrtKeyImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Decode {
private Key key;
private Cipher cipher;

public Decode(String publicKeyString) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	key = RSAPrivateCrtKeyImpl.newKey(publicKeyString.getBytes());
	cipher = Cipher.getInstance("RAS");
	cipher.init(Cipher.DECRYPT_MODE, key);
}

public String decode(
		String source) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
	byte[] encode = cipher.doFinal(source.getBytes());
	if (encode != null) {
		return new String(encode, "utf-8");
	} else {
		return null;
	}
}
}

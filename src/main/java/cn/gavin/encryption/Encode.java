/*
 * Encode.java
 * Date: 12/21/2015
 * Time: 4:51 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin.encryption;

import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;

public class Encode {
private Key key;
private Cipher cipher;
public Encode(String publicKeyString) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	ArrayList<Byte> bytes = new ArrayList<Byte>();
	for(String s : publicKeyString.split(",")){
		bytes.add(Byte.valueOf(s.trim()));
	}
	byte[] publicBytes = new byte[bytes.size()];
	for(int i = 0; i< bytes.size(); i++){
		publicBytes[i] = bytes.get(i);
	}
	key = new RSAPublicKeyImpl(publicBytes);
	cipher = Cipher.getInstance("RSA");
	cipher.init(Cipher.ENCRYPT_MODE, key);
}
public String encode(String source) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
	byte[] encode = cipher.doFinal(source.getBytes("GB2312"));
	if(encode!=null){
		return Arrays.toString(encode).replaceAll("\\[|\\]","");
	}else{
		return null;
	}
}
}

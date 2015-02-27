/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
	
	public static byte[] encrypt(String key, String string) {
		try {
			Key key2 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key2);
			return cipher.doFinal(string.getBytes());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static byte[] decrypt(String key, byte[] encrypteddata) {
		try {
			Key key2 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key2);
			return cipher.doFinal(encrypteddata);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
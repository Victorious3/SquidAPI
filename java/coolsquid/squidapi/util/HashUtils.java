/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.hash.Hashing;

public class HashUtils {

	public static EncryptedData encrypt(String key, String string) {
		return encrypt(key, string.getBytes());
	}

	public static EncryptedData encrypt(String key, byte[] bytes) {
		return encrypt(key.getBytes(), bytes);
	}

	public static EncryptedData encrypt(byte[] key, String string) {
		return encrypt(key, string.getBytes());
	}

	public static EncryptedData encrypt(byte[] key, byte[] bytes) {
		return encrypt(new SecretKeySpec(key, "AES"), bytes);
	}

	public static EncryptedData encrypt(Key key, byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return new EncryptedData(cipher.doFinal(bytes), key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] decrypt(String key, byte[] bytes) {
		try {
			Key key2 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key2);
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] decrypt(Key key, byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static long hash(String input) {
		return hash(input.getBytes());
	}

	public static long hash(byte[] input) {
		return Hashing.sha512().hashBytes(input).asLong();
	}

	public static <E, T> int hash(Map<E, T> map) {
		int result = 1;
		for (E a: map.keySet()) {
			result += (map.get(a).hashCode() * a.hashCode());
		}
		return result;
	}

	public static class EncryptedData {

		private final byte[] bytes;
		private final Key key;

		private EncryptedData(byte[] bytes, Key key) {
			this.bytes = bytes;
			this.key = key;
		}

		public byte[] getBytes() {
			return this.bytes;
		}

		public Key getKey() {
			return this.key;
		}

		public byte[] decrypt() {
			return HashUtils.decrypt(this.key, this.bytes);
		}

		@Override
		public String toString() {
			return new String(this.bytes);
		}
	}

	public static SecretKey generateKey() {
		try {
			return KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import org.apache.commons.lang3.CharSet;

public class Charsets {
	
	public static final CharSet vowels = CharSet.getInstance("aeiouAEIOU");
	
	public static boolean isRandomLetters(String string) {
		int a = 0;
		int b = 0;
		for (char c: string.toCharArray()) {
			if (vowels.contains(c)) {
				a++;
				if (a > 3) {
					return true;
				}
			}
			else {
				a = 0;
				b++;
				if (b > 3) {
					return true;
				}
			}
		}
		return false;
	}
}
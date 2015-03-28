/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import org.apache.commons.lang3.CharSet;

public class Charsets {

	public static final String LINE = System.getProperty("line.separator");

	public static final CharSet vowels = CharSet.getInstance("aeiouAEIOU");
	public static final CharSet punctuation = CharSet.getInstance(".!?");

	public static boolean isRandomLetters(String string) {
		int a = 0;
		int b = 0;
		for (char c: string.toCharArray()) {
			if (vowels.contains(c)) {
				b = 0;
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

	public static String punctuate(String string) {
		if (string == null || string.isEmpty()) {
			return ".";
		}
		if (!punctuation.contains(string.charAt(string.length() - 1))) {
			string += ".";
		}
		return string;
	}
}
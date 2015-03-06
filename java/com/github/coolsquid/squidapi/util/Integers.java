/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import org.apache.commons.lang3.CharSet;

public class Integers {
	
	public static String trimToInt(String string) {
		StringBuilder builder = new StringBuilder();
		if (string.startsWith("-")) {
			builder.append("-");
		}
		for (char a: string.toCharArray()) {
			if (CharSet.ASCII_NUMERIC.contains(a)) {
				builder.append(a);
			}
		}
		return builder.toString();
	}
	
	public static int parseInt(String string) {
		return Integer.parseInt(trimToInt(string));
	}
	
	public static int averageOf(int... ints) {
		int result = 0;
		for (int i: ints) {
			result += i;
		}
		result /= ints.length;
		return result;
	}
	
	public static int biggestOf(int... ints) {
		int result = 0;
		for (int i: ints) {
			if (result != 0) {
				if (i > result) {
					result = i;
				}
				break;
			}
			int counter = 0;
			for (int i2: ints) {
				if (i >= i2) {
					counter++;
				}
			}
			if (counter > ints.length - 1) {
				result = i;
			}
		}
		return result;
	}
	
	public static int smallestOf(int... ints) {
		int result = 0;
		for (int i: ints) {
			if (result != 0) {
				if (i < result) {
					result = i;
				}
				break;
			}
			int counter = 0;
			for (int i2: ints) {
				if (i <= i2) {
					counter++;
				}
			}
			if (counter > ints.length - 1) {
				result = i;
			}
		}
		return result;
	}
	
	public static int totalOf(int... ints) {
		int total = 0;
		for (int i: ints) {
			total += i;
		}
		return total;
	}
	
	public static int squareRootOf(int a) {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if (i * i == a) {
				return i;
			}
		}
		return 0;
	}
}
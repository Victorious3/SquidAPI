/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.List;

import org.apache.commons.lang3.CharSet;

import com.google.common.collect.Lists;

public class IntUtils {
	
	public static String trim(String string) {
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
		return Integer.parseInt(trim(string));
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
	
	public static int sumOf(int... ints) {
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
	
	public static int amountOf(int a, int... ints) {
		int result = 0;
		for (int i: ints) {
			if (i == a) {
				result++;
			}
		}
		return result;
	}
	
	public static boolean isPrimeNumber(int a) {
		for (int i = 2; i < a; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Integer> factorsOf(int a) {
		List<Integer> result = Lists.newArrayList();
		for (int i = 2; i < a; i++) {
			if (a % i == 0) {
				result.add(i);
			}
		}
		return result;
	}
	
	public static int percentOf(int a, int b) {
		return a * 100 / b;
	}

	public static int byteArrayToInteger(byte[] bytes) {
		int result = 0;
		for (byte a: bytes) {
			result += a;
		}
		return result;
	}
}
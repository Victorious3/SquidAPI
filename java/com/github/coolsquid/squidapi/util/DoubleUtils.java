package com.github.coolsquid.squidapi.util;

import java.util.List;

import org.apache.commons.lang3.CharSet;

import com.google.common.collect.Lists;

public class DoubleUtils {
	
	public static String trim(String string) {
		StringBuilder builder = new StringBuilder();
		if (string.startsWith("-")) {
			builder.append("-");
		}
		for (char a: string.toCharArray()) {
			if (CharSet.ASCII_NUMERIC.contains(a) || a == '.') {
				builder.append(a);
			}
		}
		return builder.toString();
	}
	
	public static double parseDouble(String string) {
		return Double.parseDouble(trim(string));
	}
	
	public static double averageOf(double... values) {
		double result = 0;
		for (double i: values) {
			result += i;
		}
		result /= values.length;
		return result;
	}
	
	public static double biggestOf(double... values) {
		double result = 0;
		for (double i: values) {
			if (result != 0) {
				if (i > result) {
					result = i;
				}
				break;
			}
			double counter = 0;
			for (double i2: values) {
				if (i >= i2) {
					counter++;
				}
			}
			if (counter > values.length - 1) {
				result = i;
			}
		}
		return result;
	}
	
	public static double smallestOf(double... values) {
		double result = 0;
		for (double i: values) {
			if (result != 0) {
				if (i < result) {
					result = i;
				}
				break;
			}
			double counter = 0;
			for (double i2: values) {
				if (i <= i2) {
					counter++;
				}
			}
			if (counter > values.length - 1) {
				result = i;
			}
		}
		return result;
	}
	
	public static double sumOf(double... values) {
		double total = 0;
		
		
		for (double i: values) {
			total += i;
		}
		return total;
	}
	
	public static double squareRootOf(double a) {
		for (double i = 0; i < Integer.MAX_VALUE; i++) {
			if (i * i == a) {
				return i;
			}
		}
		return 0;
	}
	
	public static double amountOf(double a, double... values) {
		double result = 0;
		for (double i: values) {
			if (i == a) {
				result++;
			}
		}
		return result;
	}
	
	public static boolean isPrimeNumber(double a) {
		for (double i = 2; i < a; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Double> factorsOf(double a) {
		List<Double> result = Lists.newArrayList();
		for (double i = 2; i < a; i++) {
			if (a % i == 0) {
				result.add(i);
			}
		}
		return result;
	}
	
	public static double percentOf(double a, double b) {
		return a * 100 / b;
	}
}
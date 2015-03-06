package com.github.coolsquid.squidapi.util;

import com.google.common.collect.ImmutableSet;

public class Integers {
	
	private static final ImmutableSet<Integer> integers = Utils.newImmutableSet('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

	public static ImmutableSet<Integer> getIntegers() {
		return integers;
	}
	
	public static int parseInt(String string) {
		StringBuilder builder = new StringBuilder();
		for (char a: string.toCharArray()) {
			if (Integers.getIntegers().contains(a)) {
				builder.append(a);
			}
		}
		return Integer.parseInt(builder.toString());
	}
}
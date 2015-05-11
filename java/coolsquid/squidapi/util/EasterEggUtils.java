/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.List;

import com.google.common.collect.Lists;

public class EasterEggUtils {
	public static final boolean APRIL_FOOLS = DateUtils.dateMatches(1, 4);
	public static final boolean HALLOWEEN = DateUtils.dateMatches(31, 10);
	public static final boolean EASTER = DateUtils.dateMatches(5, 4);
	public static final boolean CHRISTMAS = DateUtils.dateMatches(24, 12);

	private static final List<String> QUOTES = Lists.newArrayList("I have a dream, that one day this community will rise up, and defend its creators. I have a dream, that one day, the rights of modders will again be respected. I have a dream, that one day we will defeat 9minecraft!");

	public static String getRandomQuote() {
		return QUOTES.get(Utils.getRandInt(0, QUOTES.size() - 1));
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

public class EasterEggUtils {
	public static final boolean APRIL_FOOLS = DateUtils.dateMatches(1, 4);
	public static final boolean HALLOWEEN = DateUtils.dateMatches(31, 10);
	public static final boolean EASTER = DateUtils.dateMatches(5, 4);
	public static final boolean CHRISTMAS = DateUtils.dateMatches(24, 12);
}
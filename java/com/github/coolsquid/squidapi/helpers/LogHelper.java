/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.coolsquid.squidapi.SquidAPI;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.Utils;

public class LogHelper {
	
	public static final Logger logger = LogManager.getLogger(ModInfo.modid);
	
	private static void log(Level level, String msg) {
		logger.log(level, msg);
		SquidAPI.logger.log("SquidAPI", com.github.coolsquid.squidapi.logging.Level.getLevel(level.toString()), msg, false);
	}
	
	public static void info(Object... msg) {
		log(Level.INFO, Utils.newString(msg));
	}
	
	public static void warn(Object... msg) {
		log(Level.WARN, Utils.newString(msg));
	}
	
	public static void error(Object... msg) {
		log(Level.ERROR, Utils.newString(msg));
	}
	
	public static void fatal(Object... msg) {
		log(Level.FATAL, Utils.newString(msg));
	}
	
	public static void bigWarning(Level level, Object... msg) {
		String a = Utils.newString(msg);
		String b = Utils.repeat('#', a.length());
		log(level, b);
		for (String c: a.split(Utils.newLine())) {
			log(level, c);
		}
		log(level, b);
	}
}
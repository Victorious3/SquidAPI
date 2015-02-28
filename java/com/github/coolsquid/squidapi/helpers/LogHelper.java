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

public class LogHelper {
	
	public static final Logger logger = LogManager.getLogger(ModInfo.modid);
	
	private static void log(Level level, String msg) {
		logger.log(level, msg);
		SquidAPI.logger.log("SquidAPI", com.github.coolsquid.squidapi.logging.Level.getLevel(level.toString()), msg, false);
	}
	
	public static void info(Object... msg) {
		StringBuilder builder = new StringBuilder();
		for (Object string: msg) {
			builder.append(string);
		}
		log(Level.INFO, builder.toString());
	}
	
	public static void warn(Object... msg) {
		StringBuilder builder = new StringBuilder();
		for (Object string: msg) {
			builder.append(string);
		}
		log(Level.WARN, builder.toString());
	}
	
	public static void error(Object... msg) {
		StringBuilder builder = new StringBuilder();
		for (Object string: msg) {
			builder.append(string);
		}
		log(Level.ERROR, builder.toString());
	}
	
	public static void fatal(Object... msg) {
		StringBuilder builder = new StringBuilder();
		for (Object string: msg) {
			builder.append(string);
		}
		log(Level.FATAL, builder.toString());
	}
	
	public static void bigWarning(Level level, Object... msg) {
		StringBuilder builder = new StringBuilder();
		for (Object string: msg) {
			builder.append(string);
		}
		log(level, "-------------------------------------------------------------------------------------");
		log(level, builder.toString());
		log(level, "-------------------------------------------------------------------------------------");
	}
}
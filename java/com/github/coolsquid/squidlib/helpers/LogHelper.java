package com.github.coolsquid.squidlib.helpers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import com.github.coolsquid.squidlib.util.Data;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 * @see Logger.class for more advanced logging.
 */

public final class LogHelper {
	
	public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Data.modid);
	
	public static void info(String msg) {
		logger.log(Level.INFO, msg);
		}
	
	public static void info(int i) {
		logger.log(Level.INFO, i + "");
		}
	
	public static void warn(String msg) {
		logger.log(Level.WARN, msg);
		}
	
	public static void error(String msg) {
		logger.log(Level.ERROR, msg);
		}
	
	public static void error(Throwable t) {
		logger.log(Level.ERROR, t + "");
		}
	
	public static void fatal(String msg) {
		logger.log(Level.FATAL, msg);
		}
	
	public static void bigWarning(Level level, String msg) {
		logger.log(level, "-------------------------------------------------------------------------------------");
		logger.log(level, msg);
		logger.log(level, "-------------------------------------------------------------------------------------");
		}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.logging;

import java.util.Map;

import com.google.common.collect.Maps;


public class Level {
	
	private static final Map<String, Level> levels = Maps.newHashMap();
	
	/**
	 * Indicates that the message is printed for information purposes.
	 */
	
	public static final Level INFO = new Level("INFO");
	
	/**
	 * Indicates that something might fail.
	 */
	
	public static final Level WARN = new Level("WARN");
	
	/**
	 * Indicates that an error has occured.
	 */
	
	public static final Level ERROR = new Level("ERROR");
	
	/**
	 * Indicates that a fatal error has occured, and that the program will exit.
	 */
	
	public static final Level FATAL = new Level("FATAL");

	private final String name;
	
	private Level(String name) {
		this.name = name;
		levels.put(name, this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Level getLevel(String name) {
		return levels.get(name);
	}
}
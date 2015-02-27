/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;


public enum Level {
	
	/**
	 * Indicates that the message is printed for information purposes.
	 */
	
	INFO("INFO"),
	
	/**
	 * Indicates that something might fail.
	 */
	
	WARN("WARN"),
	
	/**
	 * Indicates that an error has occured.
	 */
	
	ERROR("ERROR"),
	
	/**
	 * Indicates that a fatal error has occured, and that the program will exit.
	 */
	
	FATAL("FATAL");
	
	private final String name;
	
	private Level(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Level getLevel(String name) {
		for (Level a: values()) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
}
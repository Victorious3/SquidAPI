/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;

public enum Level {
	
	/**
	 * Indicates that the message is printed for information purposes.
	 */
	
	INFO,
	
	/**
	 * Indicates that something might fail.
	 */
	
	WARN,
	
	/**
	 * Indicates that an error has occured.
	 */
	
	ERROR,
	
	/**
	 * Indicates that a fatal error has occured, and that the program will exit.
	 */
	
	FATAL;
}
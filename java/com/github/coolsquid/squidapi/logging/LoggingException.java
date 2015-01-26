/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;

public class LoggingException extends RuntimeException {
	
	private static final long serialVersionUID = 528745347;
	
	private static String s;
	
	public LoggingException(String s2) {
		s = s2;
		throw this;
	}
	@Override
	public String getMessage() {
		return s;
	}
}
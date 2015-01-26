/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.exception;

public class SquidAPIException extends RuntimeException {
	
	private static final long serialVersionUID = -6117434239809129613L;
	private static Throwable t;
	private static String s;
	public SquidAPIException(String comment) {
		crash(comment);
	}
	
	public SquidAPIException() {
		crash();
	}
	
	public SquidAPIException(Throwable cause) {
		t = cause;
		crash();
	}
	
	public void crash(String comment) {
		s = comment;
		throw this;
	}
	
	public void crash() {
		s = "";
		throw this;
	}
	
	@Override
	public String getMessage() {
		return s;
	}
	
	@Override
	public Throwable getCause() {
		return t;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.exception;

public class ReflectionException extends SquidAPIException {
	private static final long serialVersionUID = 8573921006074521771L;
	
	public ReflectionException() {}
	
	public ReflectionException(String comment) {
		super(comment);
	}
}
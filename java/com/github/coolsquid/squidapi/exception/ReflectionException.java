package com.github.coolsquid.squidapi.exception;


/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class ReflectionException extends SquidAPIException {
	private static final long serialVersionUID = 8573921006074521771L;
	public ReflectionException(String comment) throws Exception {
		super(comment);
	}
	
	public ReflectionException() {
		super();
	}
}
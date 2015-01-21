package com.github.coolsquid.squidlib.reflection;

import com.github.coolsquid.squidlib.exception.SquidLibException;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class ReflectionException extends SquidLibException {
	private static final long serialVersionUID = 8573921006074521771L;
	public ReflectionException(String comment) throws Exception {
		super(comment);
	}
	
	public ReflectionException() {
		super();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.exception;

import com.github.coolsquid.squidapi.util.Utils;


public class SquidAPIException extends RuntimeException {
	
	private static final long serialVersionUID = -6117434239809129613L;
	private Throwable t = this.getCause();
	private String s;
	
	public SquidAPIException() {}
	
	public SquidAPIException(Object comment) {
		this.s = comment.toString();
	}
	
	public SquidAPIException(Object... comment) {
		this.s = Utils.newString2(comment);
	}
	
	public SquidAPIException(Throwable cause) {
		this.t = cause;
	}
	
	@Override
	public String getMessage() {
		return this.s;
	}
	
	@Override
	public Throwable getCause() {
		return this.t;
	}
}
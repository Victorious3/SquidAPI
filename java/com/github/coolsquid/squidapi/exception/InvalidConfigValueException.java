/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.exception;

public class InvalidConfigValueException extends SquidAPIException {
	private static final long serialVersionUID = 434986523;
		
	public InvalidConfigValueException(String comment) {
		super(comment);
	}
}
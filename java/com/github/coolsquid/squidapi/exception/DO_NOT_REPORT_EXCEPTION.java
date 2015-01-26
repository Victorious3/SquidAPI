/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.exception;

public class DO_NOT_REPORT_EXCEPTION extends SquidAPIException {
	private static final long serialVersionUID = 6583935765453828886L;
	
	public DO_NOT_REPORT_EXCEPTION(String comment) {
		super(comment);
	}
}
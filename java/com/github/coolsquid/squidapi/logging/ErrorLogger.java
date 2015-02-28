/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.logging;

public class ErrorLogger {
	
	private Logger logger;
	
	public ErrorLogger(Logger logger) {
		this.logger = logger;
	}

	public void log(RuntimeException t) {
		this.logger.log("---------------------------------------------------------------------------------------------------------------");
		this.logger.log(t.getClass().getName() + ": " + t.getMessage());
		for (StackTraceElement a: t.getStackTrace()) {
			this.logger.log("	" + a.toString());
		}
		this.logger.log("---------------------------------------------------------------------------------------------------------------");
	}
}
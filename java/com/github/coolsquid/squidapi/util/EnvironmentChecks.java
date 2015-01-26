/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import org.apache.logging.log4j.Level;

import com.github.coolsquid.squidapi.helpers.LogHelper;

public class EnvironmentChecks {
	
	/**
	 * Checks if the MC version is correct, and loads dev features in decompiled environments.
	 */
	
	public static final void preInit() {
		if (Utils.wrongVersion())
			LogHelper.bigWarning(Level.WARN, "MC is not running 1.7.10! Problems may occur. Do not report any errors.");
	}
}
package com.github.coolsquid.squidlib.util;

import org.apache.logging.log4j.Level;

import com.github.coolsquid.squidlib.helpers.LogHelper;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class EnvironmentChecks {
	
	/**
	 * Checks if the MC version is correct, and loads dev features in decompiled environments.
	 */
	
	public static final void preInit() {
		if (Utils.wrongVersion())
			LogHelper.bigWarning(Level.WARN, "MC is not running 1.7.10! Problems may occur. Do not report any errors.");
		
		if (Utils.developmentEnvironment) {
			LogHelper.info("Running in a dev environment.");
		}
	}
}
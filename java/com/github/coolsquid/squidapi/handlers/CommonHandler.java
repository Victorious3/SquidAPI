/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.logging.log4j.Level;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.Utils;

public class CommonHandler {

	private static final CommonHandler instance = new CommonHandler();

	public static CommonHandler instance() {
		return instance;
	}

	/**
	 * Generates a crash reporting info file and starts the environment checks.
	 */
	
	public void init() {
		if (Utils.isJavaVersionSameOrLower(6)) {
			LogHelper.bigWarning(Level.WARN, "SquidAPI may not be compatible with your Java version. Please update to Java 7 or higher.");
		}
		if (Utils.isClient()) {
			if (Utils.wrongVersion()) {
				LogHelper.bigWarning(Level.WARN, "MC is not running 1.7.10! Problems may occur. Do not report any errors.");
			}
		}
		if (Utils.isBukkit()) {
			LogHelper.warn("Running on Bukkit! No support will be given.");
		}
		if (Utils.developmentEnvironment()) {
			LogHelper.info("Running in a dev environment.");
		}
		File file = new File("./crash-reports/README-I-AM-VERY-IMPORTANT.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				w.write("Read through crash reports before posting them!");
				w.newLine();
				w.write("If you do not do this, you might be ignored.");
				w.newLine();
				w.write("Learn how to write bug reports at: http://vazkii.us/br101/.");
				w.close();
			}
			else if (file.exists()) {
				file.setLastModified(0);
			}
			file.setReadOnly();
		} catch (IOException e) {
			
		}
	}
}
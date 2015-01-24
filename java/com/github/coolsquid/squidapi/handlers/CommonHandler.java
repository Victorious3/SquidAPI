package com.github.coolsquid.squidapi.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.EnvironmentChecks;
import com.github.coolsquid.squidapi.util.Utils;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class CommonHandler {
	
	/**
	 * Generates a crash reporting info file and starts the environment checks.
	 */
	
	public static final void init() {
		File file = new File("./crash-reports/README-I-AM-VERY-IMPORTANT.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
				w.format("Read through crash reports before posting them!\n");
				w.format("If you do not do this, you might be ignored, or in worst case banned for spamming.\n");
				w.format("Learn how to write bug reports at: http://vazkii.us/br101/.");
				w.close();
			}
			else if (file.exists()) {
				file.setLastModified(0);
			}
			file.setReadOnly();
		} catch (IOException e) {
			
		}
		
		if (Utils.isClient())
			EnvironmentChecks.preInit();
		
		if (Utils.isBukkit())
			LogHelper.warn("Running on Bukkit! No support will be given.");
	}
}
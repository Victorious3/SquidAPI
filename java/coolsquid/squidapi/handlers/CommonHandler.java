/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;

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
			SquidAPI.instance().bigWarning("SquidAPI may not be compatible with your Java version. Please update to Java 7 or higher.");
		}
		if (Utils.isClient()) {
			if (Utils.wrongVersion()) {
				SquidAPI.instance().bigWarning("You are not using the correct MC version! Problems may occur. Do not report any errors.");
			}
		}
		if (Utils.isBukkit()) {
			SquidAPI.instance().warn("Running on Bukkit! No support will be given.");
		}
		if (Utils.developmentEnvironment()) {
			SquidAPI.instance().info("Running in a dev environment.");
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
			e.printStackTrace();
		}
		String a = Utils.getPackName();
		if (a != null) {
			SquidAPI.instance().info("Modpack: " + a);
			FMLCommonHandler.instance().registerCrashCallable(new ModpackCrashMessage(a));
		}
	}

	private class ModpackCrashMessage implements ICrashCallable {

		private final String a;

		public ModpackCrashMessage(String a) {
			this.a = a;
		}

		@Override
		public String call() throws Exception {
			return this.a;
		}

		@Override
		public String getLabel() {
			return "Modpacks: ";
		}
	}
}
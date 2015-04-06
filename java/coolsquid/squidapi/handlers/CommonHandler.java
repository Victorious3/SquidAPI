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
import coolsquid.squidapi.util.CrashCallable;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.PatreonController;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;

public class CommonHandler {

	private static final CommonHandler instance = new CommonHandler();

	public static CommonHandler instance() {
		return instance;
	}

	public void init() {
		if (Utils.isJavaVersionSameOrLower(6)) {
			SquidAPI.instance().bigWarning("SquidAPI may not be compatible with your Java version. Please update to Java 7 or higher.");
		}
		if (MiscLib.CLIENT) {
			if (Utils.wrongVersion()) {
				SquidAPI.instance().bigWarning("You are not using the correct MC version! Problems may occur. Do not report any errors.");
			}
		}
		if (MiscLib.BUKKIT) {
			SquidAPI.instance().warn("Running on Bukkit! No support will be given.");
		}
		if (MiscLib.DEV_ENVIRONMENT) {
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
			this.registerCallable(new CrashCallable("Modpack: ", a));
		}
		if (MiscLib.CLIENT) {
			PatreonController.INSTANCE.addPatreons("03a42a75-223a-4307-99c1-b69162ad6a6f", "c46c08f3-f004-443d-b8ce-340d2223a332");
		}
	}

	public void registerCallable(String label, String message) {
		this.registerCallable(new CrashCallable(label, message));
	}

	public void registerCallable(ICrashCallable callable) {
		FMLCommonHandler.instance().registerCrashCallable(callable);
	}
}
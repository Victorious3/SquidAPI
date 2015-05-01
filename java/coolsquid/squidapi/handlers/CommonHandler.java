/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import java.io.File;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.exception.LoadingException;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.RewardManager;
import coolsquid.squidapi.util.Utils;
import coolsquid.squidapi.util.io.IOUtils;
import coolsquid.squidapi.util.objects.CrashCallable;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommonHandler {

	public void init() {
		if (Utils.isJavaVersionSameOrLower(6)) {
			SquidAPI.instance().bigWarning("SquidAPI may not be compatible with your Java version. Please update to Java 7 or higher.");
		}
		if (MiscLib.CLIENT) {
			if (Utils.wrongVersion()) {
				SquidAPI.instance().bigWarning("You are not using the correct MC version! Problems may occur. Do not report any errors.");
			}
			RewardManager.INSTANCE.addSpecialUsers("03a42a75-223a-4307-99c1-b69162ad6a6f", "c46c08f3-f004-443d-b8ce-340d2223a332");
		}
		if (MiscLib.DEV_ENVIRONMENT) {
			SquidAPI.instance().info("Running in a dev environment.");
		}
		File file = new File("./crash-reports/README-I-AM-VERY-IMPORTANT.txt");
		if (!file.exists()) {
			IOUtils.writeLines(file, "Read through crash reports before posting them!", "If you do not do this, you might be ignored.", "Learn how to write bug reports at: http://vazkii.us/br101/.");
			file.setReadOnly();
		}
		else if (file.exists()) {
			file.setLastModified(0);
		}
		String a = Utils.getPackName();
		if (a != null) {
			SquidAPI.instance().info("Modpack: " + a);
			this.registerCallable(new CrashCallable("Modpack: ", a));
		}
	}

	public void registerCallable(String label, String message) {
		this.registerCallable(new CrashCallable(label, message));
	}

	public void registerCallable(ICrashCallable callable) {
		FMLCommonHandler.instance().registerCrashCallable(callable);
	}

	/** Clientside only!!! */
	@SideOnly(Side.CLIENT)
	public void throwNewLoadingException() {
		throw new LoadingException("There was a severe error during mod loading. Erroring mod: " + Utils.getCurrentMod().getName() + ".");
	}
}
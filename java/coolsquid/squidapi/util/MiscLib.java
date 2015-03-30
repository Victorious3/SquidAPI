/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.io.File;
import java.util.Properties;

import coolsquid.squidapi.logging.Logger;
import cpw.mods.fml.common.Loader;

public class MiscLib {

	public static final String LINE = System.lineSeparator();
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final Runtime RUNTIME = Runtime.getRuntime();
	public static final Properties SYSTEM_PROPERTIES = System.getProperties();

	public static final Logger LOGGER;

	public static final SquidAPIProperties SETTINGS = new SquidAPIProperties();
	public static final SquidAPIProperties NICKNAMES = new SquidAPIProperties();

	public static boolean updateChecker() {
		return Utils.isClient() && !Loader.isModLoaded("VersionChecker") && SETTINGS.getBoolean("updateChecker");
	}

	static {
		File file = new File("./logs/SquidAPI.log");
		if (file.exists()) {
			file.delete();
		}
		LOGGER = new Logger(file);

		NICKNAMES.set("Eyamaz", "Eyamapple");
		NICKNAMES.set("CoolSquid", "Squiddy");
		NICKNAMES.set("MyLittleSquiddy", "Squiddy");
		NICKNAMES.set("VictiniX888", "ToiletSeatLover");
	}
}
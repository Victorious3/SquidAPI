/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.io.File;

import coolsquid.squidapi.util.MiscLib;

public class ModConfigHandler extends ConfigHandler {

	public static final ModConfigHandler INSTANCE = new ModConfigHandler(new File("./config/SquidAPI/SquidAPI.cfg"));

	private ModConfigHandler(File file) {
		super(file);
	}

	public static final String CATEGORY_GENERAL = "General";
	public static final String CATEGORY_BIOME_IDS = "General";

	@Override
	public void loadConfig() {
		this.config.setCategoryComment(CATEGORY_GENERAL, "General options.");
		MiscLib.SETTINGS.set("cleanMenu", this.config.getBoolean("cleanMenu", CATEGORY_GENERAL, false, "Cleans up the main menu a bit."));
		MiscLib.SETTINGS.set("branding", this.config.getString("branding", CATEGORY_GENERAL, "", "Will show up besides the Forge branding on the main menu. Not affected by \"cleanMenu\"."));
		MiscLib.SETTINGS.set("updateChecker", this.config.getBoolean("updateChecker", CATEGORY_GENERAL, true, "Enables the update checker."));
		MiscLib.SETTINGS.set("easterEggs", this.config.getBoolean("easterEggs", CATEGORY_GENERAL, true, "Enables all easter eggs."));

		if (this.config.hasChanged()) {
			this.config.save();
		}
	}
}
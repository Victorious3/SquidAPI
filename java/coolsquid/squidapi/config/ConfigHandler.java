/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import coolsquid.squidapi.util.MiscLib;

public class ConfigHandler {
	
	public ConfigHandler(File configfile) {
		this.config = new Configuration(configfile);
	}

	private Configuration config;
	
	public void preInit() {
		this.initCategories();
		this.readConfig();
	}
	
	private final String CATEGORY_GENERAL = "General";
	
	private void initCategories() {
		this.config.setCategoryComment(this.CATEGORY_GENERAL, "General options.");
	}

	private final void readConfig() {
		MiscLib.SETTINGS.set("cleanMenu", this.config.getBoolean("cleanMenu", this.CATEGORY_GENERAL, false, "Cleans up the main menu a bit."));
		MiscLib.SETTINGS.set("branding", this.config.getString("branding", this.CATEGORY_GENERAL, "", "Will show up besides the Forge branding on the main menu. Not affected by \"cleanMenu\"."));
		MiscLib.SETTINGS.set("updateChecker", this.config.getBoolean("updateChecker", this.CATEGORY_GENERAL, true, "Enables the update checker."));
		MiscLib.SETTINGS.set("easterEggs", this.config.getBoolean("easterEggs", this.CATEGORY_GENERAL, true, "Enables all easter eggs."));

		if (this.config.hasChanged()) {
			this.config.save();
		}
	}
}
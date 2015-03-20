/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

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
	
	public static boolean cleanMenu;
	public static String branding = "";

	private final void readConfig() {
		cleanMenu = this.config.getBoolean("cleanMenu", this.CATEGORY_GENERAL, false, "Cleans up the main menu a bit.");
		branding = this.config.getString("branding", this.CATEGORY_GENERAL, "", "Will show up besides the Forge branding on the main menu. Not affected by \"cleanMenu\".");

		if (this.config.hasChanged()) {
			this.config.save();
		}
	}
}
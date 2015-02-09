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
		initCategories();
		readConfig();
	}
	
	private final String CATEGORY_GENERAL = "General";
	
	private void initCategories() {
		config.setCategoryComment(CATEGORY_GENERAL, "General options.");
	}
	
	private final void readConfig() {
		if (config.hasChanged()) {
			config.save();
		}
	}
}
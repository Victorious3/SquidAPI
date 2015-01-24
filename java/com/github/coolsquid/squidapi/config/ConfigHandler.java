package com.github.coolsquid.squidapi.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class ConfigHandler {
	
	/**
	 * The config file.
	 */
	
	private static File configFile;
	
	/**
	 * The configuration.
	 */
	
	private static Configuration config;
	
	public static final void preInit(File file) {
		configFile = file;
		createConfig();
		initCategories();
		readConfig();
	}
	
	private static final void createConfig() {
		if (config == null)
			config = new Configuration(configFile);
	}
	
	/*
	 * Available categories. DO NOT MODIFY, as it breaks configs.
	 */
	
	/**
	 * For options that doesn't fit elsewhere.
	 */
	
	private static final String CATEGORY_GENERAL = "General";
	
	/**
	 * For intermod compatibility.
	 */
	
	private static final String CATEGORY_COMPAT = "Compatibility";

	/**
	 * Sets category comments.
	 */
	
	private static void initCategories() {
		config.setCategoryComment(CATEGORY_GENERAL, "General options.");
		config.setCategoryComment(CATEGORY_COMPAT, "Compatibility options.");
	}
	
	/**
	 * Reads the config.
	 */
	
	private static final void readConfig() {
		if (config.hasChanged()) {
			config.save();
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.compat;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.IntUtils;

import cpw.mods.fml.common.API;
import cpw.mods.fml.common.Loader;

public enum Compat {
	
	ThermalExpansion("ThermalExpansion"),
	Botania("Botania", "vazkii.botania.api", "35"),
	RotaryCraft("RotaryCraft"),
	AppliedEnergistics2("appliedenergistics2", "appeng.api", "rv2"),
	BloodMagic("WayOfTime"),
	ThaumCraft("ThaumCraft", "thaumcraft.api", "4.2.2.0"),
	RailCraft("RailCraft", "mods.railcraft.api.crafting", "1.0.0");

	private final boolean enabled;
	private final Package api;
	private final int version;
	
	private Compat(String modid) {
		this.enabled = Loader.isModLoaded(modid);
		this.api = null;
		this.version = 0;
		if (this.enabled) {
			LogHelper.info(modid, " is loaded. Enabling ", modid, " compatibility.");
		}
		else {
			LogHelper.info(modid, " is not loaded. Not enabling ", modid, " compatibility.");
		}
	}
	
	private Compat(String modid, String api, String version) {
		this.enabled = Loader.isModLoaded(modid);
		this.api = Package.getPackage(api);
		this.version = IntUtils.parseInt(version);
		if (this.enabled) {
			this.checkAPI();
			LogHelper.info(modid, " is loaded. Enabling ", modid, " compatibility.");
		}
		else {
			LogHelper.info(modid, " is not loaded. Not enabling ", modid, " compatibility.");
		}
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	private void checkAPI() {
		try {
			int versionnumber = IntUtils.parseInt(this.api.getAnnotation(API.class).apiVersion());
			if (versionnumber > this.version) {
				LogHelper.warn("The version of ", this.toString(), " loaded is newer than the version SquidAPI was made with. Problems may occur. Please contact CoolSquid.");
			}
			else if (versionnumber < this.version) {
				LogHelper.warn("The version of ", this.toString(), " loaded is older than the version SquidAPI was made with. Problems may occur. Please update ", this.toString(), ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
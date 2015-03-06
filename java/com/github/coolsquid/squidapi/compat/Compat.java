/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.compat;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.Integers;

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

	private final boolean loadCompat;
	private final Package api;
	private final int version;
	
	private Compat(String modid) {
		this.loadCompat = Loader.isModLoaded(modid);
		this.api = null;
		this.version = 0;
	}
	
	private Compat(String modid, String api, String version) {
		this.loadCompat = Loader.isModLoaded(modid);
		this.api = Package.getPackage(api);
		this.version = Integers.parseInt(version);
		if (this.loadCompat) {
			this.checkAPI();
		}
	}

	public boolean loadCompat() {
		return this.loadCompat;
	}
	
	private void checkAPI() {
		int versionnumber = Integers.parseInt(this.api.getAnnotation(API.class).apiVersion());
		if (versionnumber > this.version) {
			LogHelper.warn("The version of ", this.toString(), " loaded is newer than the version SquidAPI was made with. Problems may occur. Please contact CoolSquid.");
		}
		else if (versionnumber < this.version) {
			LogHelper.warn("The version of ", this.toString(), " loaded is older than the version SquidAPI was made with. Problems may occur. Please update ", this.toString(), ".");
		}
	}
}
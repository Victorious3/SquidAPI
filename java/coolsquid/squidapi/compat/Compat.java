/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.IntUtils;
import cpw.mods.fml.common.API;
import cpw.mods.fml.common.Loader;

public enum Compat {
	
	THERMALEXPANSION("ThermalExpansion"),
	BOTANIA("Botania", "vazkii.botania.api", "35"),
	ROTARYCRAFT("RotaryCraft"),
	APPLIEDENERGISTICS2("appliedenergistics2", "appeng.api", "rv2"),
	BLOODMAGIC("AWWayofTime"),
	THAUMCRAFT("ThaumCraft", "thaumcraft.api", "4.2.2.0"),
	RAILCRAFT("RailCraft", "mods.railcraft.api.crafting", "1.0.0"),
	ENVIROMINE("enviromine"),
	MINECRAFT("minecraft");

	private final boolean enabled;
	private final Package api;
	private final int version;
	
	private Compat(String modid) {
		this.enabled = Loader.isModLoaded(modid);
		this.api = null;
		this.version = 0;
		if (this.enabled) {
			SquidAPI.instance().info(modid, " is loaded. Enabling ", modid, " compatibility.");
		}
		else {
			SquidAPI.instance().info(modid, " is not loaded. Not enabling ", modid, " compatibility.");
		}
	}
	
	private Compat(String modid, String api, String version) {
		this.enabled = Loader.isModLoaded(modid);
		this.api = Package.getPackage(api);
		this.version = IntUtils.parseInt(version);
		if (this.enabled) {
			this.checkAPI();
			SquidAPI.instance().info(modid, " is loaded. Enabling ", modid, " compatibility.");
		}
		else {
			SquidAPI.instance().info(modid, " is not loaded. Not enabling ", modid, " compatibility.");
		}
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	private void checkAPI() {
		try {
			int versionnumber = IntUtils.parseInt(this.api.getAnnotation(API.class).apiVersion());
			if (versionnumber > this.version) {
				SquidAPI.instance().warn("The version of ", this.toString(), " loaded is newer than the version SquidAPI was made with. Problems may occur. Please contact CoolSquid.");
			}
			else if (versionnumber < this.version) {
				SquidAPI.instance().warn("The version of ", this.toString(), " loaded is older than the version SquidAPI was made with. Problems may occur. Please update ", this.toString(), ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
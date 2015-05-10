/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.API;
import cpw.mods.fml.common.Loader;

public enum Compat {

	THERMALEXPANSION("ThermalExpansion"),
	BOTANIA("Botania", "vazkii.botania.api", "35"),
	ROTARYCRAFT("RotaryCraft"),
	BLOODMAGIC("AWWayofTime"),
	RAILCRAFT("RailCraft", "mods.railcraft.api.crafting", "1.0.0"),
	MINECRAFT("minecraft", true),
	TICON("TConstruct"),
	MINETWEAKER("MineTweaker3"),
	SQUIDUTILS("SquidUtils", "coolsquid.squidutils.api", "2.0.0"),
	STARSTONES("StarStones"),
	SAFECHAT("SafeChat"),
	UPTODATE("uptodate"),
	FORGEUPDATER("forgeupdater"),
	VERSIONCHECKER("VersionChecker");

	private final String modid;
	private final boolean enabled;
	private final API api;

	private Compat(String modid) {
		this.modid = modid;
		this.enabled = Loader.isModLoaded(modid);
		this.api = null;
		if (this.enabled) {
			SquidAPI.instance().info(modid + " is loaded. Enabling " + modid + " compatibility.");
		}
		else {
			SquidAPI.instance().info(modid + " is not loaded. Not enabling " + modid + " compatibility.");
		}
	}

	private Compat(String modid, String api, String version) {
		this.modid = modid;
		this.enabled = Loader.isModLoaded(modid);
		if (this.enabled) {
			this.api = Package.getPackage(api).getAnnotation(API.class);
			this.checkAPI(version);
			SquidAPI.instance().info(modid + " is loaded. Enabling " + modid + " compatibility.");
		}
		else {
			this.api = null;
			SquidAPI.instance().info(modid + " is not loaded. Not enabling " + modid + " compatibility.");
		}
	}

	private Compat(String modid, boolean isEnabled) {
		this.modid = modid;
		this.enabled = isEnabled;
		this.api = null;
	}

	private Compat(String modid, boolean isEnabled, String api, String version) {
		this.modid = modid;
		this.enabled = isEnabled;
		this.api = Package.getPackage(api).getAnnotation(API.class);
		if (this.enabled) {
			this.checkAPI(version);
			SquidAPI.instance().info(modid + " is loaded. Enabling " + modid + " compatibility.");
		}
		else {
			SquidAPI.instance().info(modid + " is not loaded. Not enabling " + modid + " compatibility.");
		}
	}

	public String getModid() {
		return this.modid;
	}

	public API getAPI() {
		return this.api;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	private void checkAPI(String version) {
		try {
			int versionId = IntUtils.parseInt(version);
			int versionId2 = IntUtils.parseInt(this.api.apiVersion());
			if (versionId2 > versionId) {
				SquidAPI.instance().warn("The version of " + this.toString() + " loaded is newer than the version SquidAPI was made with. Problems may occur. Please contact CoolSquid.");
			}
			else if (versionId2 < versionId) {
				SquidAPI.instance().warn("The version of " + this.toString() + " loaded is older than the version SquidAPI was made with. Problems may occur. Please update " + this.toString() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package com.github.coolsquid.squidapi.compat;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.Integers;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.API;
import cpw.mods.fml.common.Loader;

public enum Compat {
	
	ThermalExpansion(),
	Botania("vazkii.botania.api", "35"),
	RotaryCraft(),
	AppliedEnergistics2("appeng.api", "rv2");

	private final boolean loadCompat;
	private final Package api;
	private final int version;
	
	private Compat() {
		this.loadCompat = Loader.isModLoaded(this.toString());
		this.api = null;
		this.version = 0;
	}
	
	private Compat(String api, String version) {
		this.loadCompat = Loader.isModLoaded(this.toString());
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
			LogHelper.warn("The version of ", this.toString(), " loaded is newer than the version SquidUtils was made with. Problems may occur. Please contact CoolSquid.");
		}
		else if (versionnumber < this.version) {
			LogHelper.warn("The version of ", this.toString(), " loaded is older than the version SquidUtils was made with. Problems may occur. Please update ", this.toString(), ".");
		}
	}
	
	@Override
	public String toString() {
		return Utils.newString(this.name(), "API");
	}
}
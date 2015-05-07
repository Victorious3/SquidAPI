/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.List;
import java.util.Set;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.util.collect.Registry;
import cpw.mods.fml.common.Loader;

public final class ModManager {

	public static final ModManager INSTANCE = new ModManager();

	private final Registry<SquidAPIMod> mods = Registry.create();

	private ModManager() {

	}

	public void registerMod(SquidAPIMod mod) {
		this.mods.register(mod.getModid(), mod);
		mod.info("Registering SquidAPIMod ", mod.getModid(), ".");
	}

	public SquidAPIMod activeMod() {
		Object mod = Loader.instance().activeModContainer().getMod();
		if (mod instanceof SquidAPIMod) {
			return (SquidAPIMod) mod;
		}
		return SquidAPI.instance();
	}

	public Registry<SquidAPIMod> getMods() {
		return this.mods.clone();
	}

	public Set<String> getModids() {
		return this.mods.names();
	}

	public List<SquidAPIMod> getModObjects() {
		return this.mods.values();
	}

	public SquidAPIMod getMod(String modid) {
		SquidAPIMod mod = this.mods.get(modid);
		if (mod != null) {
			return mod;
		}
		return SquidAPI.instance();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Set;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.registry.Registry;
import cpw.mods.fml.common.Loader;

public final class ModManager {

	public static final ModManager INSTANCE = new ModManager();

	private final Registry<SquidAPIMod> mods = new Registry<SquidAPIMod>();

	private ModManager() {
		
	}

	public void registerMod(String modid, SquidAPIMod mod) {
		this.mods.register(modid, mod);
	}

	public SquidAPIMod activeMod() {
		Object mod = Loader.instance().activeModContainer().getMod();
		if (mod instanceof SquidAPIMod) {
			return (SquidAPIMod) mod;
		}
		return null;
	}

	public Registry<SquidAPIMod> getMods() {
		return this.mods.clone();
	}

	public Set<String> getModids() {
		return this.mods.names();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameData;

public final class VanillaBlockRegistry extends LockedRegistry<Block> {

	private static final VanillaBlockRegistry instance = new VanillaBlockRegistry();

	public static VanillaBlockRegistry instance() {
		return instance;
	}

	private VanillaBlockRegistry() {
		for (Object o : GameData.getBlockRegistry().getKeys()) {
			String key = o.toString();
			if (key.startsWith("minecraft:")) {
				this.register(key.substring(10), GameData.getBlockRegistry().getObject(key));
			}
		}
		this.lock();
	}
}

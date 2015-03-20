/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameData;

public final class VanillaItemRegistry extends LockedRegistry<Item> {

	private static final VanillaItemRegistry instance = new VanillaItemRegistry();

	public static VanillaItemRegistry instance() {
		return instance;
	}

	private VanillaItemRegistry() {
		for (Object o : GameData.getItemRegistry().getKeys()) {
			String key = o.toString();
			if (key.startsWith("minecraft:")) {
				this.register(key.substring(10), GameData.getItemRegistry().getObject(key));
			}
		}
		this.lock();
	}
}

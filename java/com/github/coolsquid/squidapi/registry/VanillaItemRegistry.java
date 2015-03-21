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
		for (Object a: GameData.getItemRegistry().getKeys()) {
			String name = a.toString();
			if (name.startsWith("minecraft:")) {
				this.register(name.substring(10), GameData.getItemRegistry().getObject(name));
			}
		}
		this.lock();
	}
	
	public boolean isVanillaItem(Item item) {
		return this.getName(item) != null;
	}
}
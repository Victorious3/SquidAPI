/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.world.WorldType;

public class WorldTypeRegistry extends Registry<WorldType> {

	private static final WorldTypeRegistry instance = new WorldTypeRegistry();

	public WorldTypeRegistry() {
		for (WorldType a: WorldType.worldTypes) {
			if (a != null) {
				this.register(a.getWorldTypeName(), a);
			}
		}
	}

	public static WorldTypeRegistry instance() {
		return instance;
	}
}
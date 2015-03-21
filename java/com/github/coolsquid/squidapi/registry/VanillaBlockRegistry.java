/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.block.Block;

public final class VanillaBlockRegistry extends LockedRegistry<Block> {

	private static final VanillaBlockRegistry instance = new VanillaBlockRegistry();

	public static VanillaBlockRegistry instance() {
		return instance;
	}

	private VanillaBlockRegistry() {
		for (Object a : Block.blockRegistry.getKeys()) {
			String name = a.toString();
			if (name.startsWith("minecraft:")) {
				this.register(name.replace("minecraft:", ""), (Block) Block.blockRegistry.getObject(a));
			}
		}
		this.lock();
	}
	
	public boolean isVanillaBlock(Block block) {
		return this.getName(block) != null;
	}
}
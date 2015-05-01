/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameData;

public final class VanillaBlockRegistry extends LockedRegistry<Block> {

	private static final VanillaBlockRegistry instance = new VanillaBlockRegistry();

	public static VanillaBlockRegistry instance() {
		return instance;
	}

	private VanillaBlockRegistry() {
		for (Object a : Block.blockRegistry.getKeys()) {
			String name = a.toString();
			if (name.startsWith("minecraft:")) {
				this.register(name.substring(10), (Block) GameData.getBlockRegistry().getObject(a));
			}
		}
		this.lock();
	}
}
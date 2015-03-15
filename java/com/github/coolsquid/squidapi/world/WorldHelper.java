/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.world;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldHelper {
	
	public static void setBlock(Block block, World world, int x, int y, int z) {
		world.setBlock(x, y, z, block, 0, 3);
	}
	
	public static void setHardcore(World world) {
		world.getWorldInfo().hardcore = true;
	}
	
	public static boolean isHardcore(World world) {
		return world.getWorldInfo().hardcore;
	}
}
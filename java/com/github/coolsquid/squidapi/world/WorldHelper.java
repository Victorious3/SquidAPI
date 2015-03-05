package com.github.coolsquid.squidapi.world;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldHelper {
	
	public static void setBlock(Block block, World world, int x, int y, int z) {
		world.setBlock(x, y, z, block, 0, 3);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.world;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import coolsquid.squidapi.util.MiscLib;

public class WorldHelper {

	private World world;

	private WorldHelper(World world) {
		this.world = world;
	}

	private static WorldHelper instance;

	public static WorldHelper instance(World world) {
		return new WorldHelper(world);
	}

	public static WorldHelper instance() {
		if (MiscLib.CLIENT) {
			instance = new WorldHelper(Minecraft.getMinecraft().theWorld);
		}
		else {
			instance = new WorldHelper(MinecraftServer.getServer().worldServerForDimension(0));
		}
		return instance;
	}

	public void setBlock(Block block, int x, int y, int z) {
		this.world.setBlock(x, y, z, block, 0, 3);
	}

	public void setHardcore() {
		this.world.getWorldInfo().hardcore = true;
	}

	public boolean isHardcore() {
		return this.world.getWorldInfo().hardcore;
	}

	public int getDimensionId() {
		return this.world.provider.dimensionId;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.explosion;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import com.github.coolsquid.squidapi.util.Utils;

public class SquidAPIExplosion {
	
	private Entity entity;
	private float size;
	private boolean fire;
	
	public SquidAPIExplosion(Entity entity2, float size2, boolean fire2) {
		entity = entity2;
		size = size2;
		fire = fire2;
	}
	
	public void explode() {
		for (int a = 0; a < size * 40; a++) {
			int x = Utils.getRandInt(-15, 15);
			int y = Utils.getRandInt(-2, 2);
			int z = Utils.getRandInt(-15, 15);
			int b = 0;
			while (!setBlock(entity.worldObj, entity.posX + x, entity.posY + y, entity.posZ + z, Blocks.air) && b < 5) {
				setBlock(entity.worldObj, entity.posX + x, entity.posY + y - 1, entity.posZ + z, Blocks.air);
				b++;
			}
			b = 0;
		}
		for (int a = 0; a < size * 30; a++) {
			int x = Utils.getRandInt(-10, 10);
			int y = Utils.getRandInt(-5, 5);
			int z = Utils.getRandInt(-10, 10);
			int b = 0;
			while (!setBlock(entity.worldObj, entity.posX + x, entity.posY + y, entity.posZ + z, Blocks.air) && b < 5) {
				setBlock(entity.worldObj, entity.posX + x, entity.posY + y - 1, entity.posZ + z, Blocks.air);
				b++;
			}
			b = 0;
		}
		for (int a = 0; a < size * 15; a++) {
			int x = Utils.getRandInt(-5, 5);
			int y = Utils.getRandInt(-10, 10);
			int z = Utils.getRandInt(-5, 5);
			int b = 0;
			while (!setBlock(entity.worldObj, entity.posX + x, entity.posY + y, entity.posZ + z, Blocks.air) && b < 5) {
				setBlock(entity.worldObj, entity.posX + x, entity.posY + y - 1, entity.posZ + z, Blocks.air);
				b++;
			}
			b = 0;
		}
		if (fire)
			explodeB();
	}
	
	public void explodeB() {
		for (int a = 0; a < size * 30; a++) {
			int x = Utils.getRandInt(-10, 10);
			int y = Utils.getRandInt(-10, 0);
			int z = Utils.getRandInt(-10, 10);
			int b = 0;
			while (!setBlock(entity.worldObj, entity.posX + x, entity.posY + y, entity.posZ + z, Blocks.fire) && b < 5) {
				setBlock(entity.worldObj, entity.posX + x, entity.posY + y - 1, entity.posZ + z, Blocks.fire);
				b++;
			}
			b = 0;
		}
	}

	public static boolean setBlock(World world, double x, double y, double z, Block block) {
		if (world.getBlock((int)x, (int)y, (int)z).isOpaqueCube()) {
			world.setBlock((int)x, (int)y, (int)z, block, 0, 2);
			return true;
		}
		return false;
	}
}
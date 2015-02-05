/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import com.github.coolsquid.squidapi.registry.Registry;
import com.github.coolsquid.squidapi.util.ModInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBasic extends Block {
	
	public static final Material material = new Material(MapColor.stoneColor);
			
	public BlockBasic(String name) {
		super(material);
		setBlockTextureName(ModInfo.modid + ":" + name);
		initBlock(name);
	}
	
	public BlockBasic(String name, String textureName) {
		super(material);
		setBlockTextureName(ModInfo.modid + ":" + textureName);
		initBlock(name);
	}
	
	public void initBlock(String name) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		setHardness(1.5F);
		setHarvestLevel("pickaxe", 3);
		setResistance(10F);
		setStepSound(Block.soundTypeStone);
		blockRegistry.register(this);
	}
	
	private static final Registry blockRegistry = new Registry();
	
	public int mobility = 0;
	
	@Override
	public int getMobilityFlag() {
		return mobility;
	}
	
	@Override
	public String toString() {
		return "BlockBasic [textureName=" + textureName + ", opaque=" + opaque
				+ ", lightOpacity=" + lightOpacity + ", canBlockGrass="
				+ canBlockGrass + ", lightValue=" + lightValue
				+ ", useNeighborBrightness=" + useNeighborBrightness
				+ ", blockHardness=" + blockHardness + ", blockResistance="
				+ blockResistance + ", blockConstructorCalled="
				+ blockConstructorCalled + ", enableStats=" + enableStats
				+ ", needsRandomTick=" + needsRandomTick
				+ ", isBlockContainer=" + isBlockContainer + ", minX=" + minX
				+ ", minY=" + minY + ", minZ=" + minZ + ", maxX=" + maxX
				+ ", maxY=" + maxY + ", maxZ=" + maxZ + ", stepSound="
				+ stepSound + ", blockParticleGravity=" + blockParticleGravity
				+ ", blockMaterial=" + blockMaterial + ", slipperiness="
				+ slipperiness + ", blockIcon=" + blockIcon + ", delegate="
				+ delegate + ", harvesters=" + harvesters + ", captureDrops="
				+ captureDrops + ", capturedDrops=" + capturedDrops + "]";
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		return toString().equals(object);
	}
}
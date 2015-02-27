/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;

import com.github.coolsquid.squidapi.util.ModInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBasic extends Block {
	
	private static final Material material = new Material(MapColor.stoneColor);

	private int mobility = 0;
	private boolean canEntityDestroy = true;
	private boolean isLadder;
	private boolean canCreatureSpawn = true;
	private boolean canDropFromExplosion = true;
		
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
	
	public BlockBasic initBlock(String name) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		setHardness(1.5F);
		setHarvestLevel("pickaxe", 3);
		setResistance(10F);
		setStepSound(Block.soundTypeStone);
		return this;
	}

	public BlockBasic setMobility(int mobility) {
		this.mobility = mobility;
		return this;
	}

	public BlockBasic setCanEntityDestroy(boolean canEntityDestroy) {
		this.canEntityDestroy = canEntityDestroy;
		return this;
	}
	
	public BlockBasic setIsLadder(boolean isLadder) {
		this.isLadder = isLadder;
		return this;
	}

	public BlockBasic setCanCreatureSpawn(boolean canCreatureSpawn) {
		this.canCreatureSpawn = canCreatureSpawn;
		return this;
	}

	public BlockBasic setCanDropFromExplosion(boolean canDropFromExplosion) {
		this.canDropFromExplosion = canDropFromExplosion;
		return this;
	}

	@Override
	public int getMobilityFlag() {
		return mobility;
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return canEntityDestroy;
	}
	
	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		return isLadder;
	}
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return canCreatureSpawn;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return canDropFromExplosion;
	}
}
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
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBasic extends Block {
	
	private static final Material material = new Material(MapColor.stoneColor);

	public int mobility = 0;
	public boolean canEntityDestroy = true;
	public boolean isLadder;
	public boolean canCreatureSpawn = true;
	public boolean canDropFromExplosion = true;

	public boolean isFireSource;
	
	public BlockBasic(String name) {
		super(material);
		this.setBlockTextureName(Utils.newString(Loader.instance().activeModContainer().getModId(), ":", name));
		this.initBlock(name);
	}
	
	public BlockBasic(String name, String textureName) {
		super(material);
		this.setBlockTextureName(Utils.newString(Loader.instance().activeModContainer().getModId(), ":", textureName));
		this.initBlock(name);
	}
	
	public BlockBasic initBlock(String name) {
		this.setBlockName(name);
		GameRegistry.registerBlock(this, name);
		this.setHardness(1.5F);
		this.setHarvestLevel("pickaxe", 3);
		this.setResistance(10F);
		this.setStepSound(Block.soundTypeStone);
		return this;
	}
	
	@Override
	public int getMobilityFlag() {
		return this.mobility;
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		return this.canEntityDestroy;
	}
	
	@Override
	public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		return this.isLadder;
	}
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return this.canCreatureSpawn;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosion) {
		return this.canDropFromExplosion;
	}
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
		return this.isFireSource;
	}
}
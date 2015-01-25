package com.github.coolsquid.squidapi.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

import com.github.coolsquid.squidapi.logging.Logger;
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
		setResistance(10F);
		setStepSound(Block.soundTypeStone);
		blockRegistry.register(this);
	}
	
	public static final Registry blockRegistry = new Registry();
	
	public static void dumpBlockNames() {
		Logger logger = new Logger("", "SquidBlocks");
		for (int a = 0; a < blockRegistry.size(); a++) {
			logger.log(((BlockBasic) blockRegistry.getObjectFromId(a)).getUnlocalizedName());
		}
		logger.save(false);
	}
	
	@Override
	public int getMobilityFlag() {
		return 2;
	}
	
	@Override
	public String toString() {
		return getUnlocalizedName();
	}
}
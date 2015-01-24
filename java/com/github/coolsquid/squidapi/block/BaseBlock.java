package com.github.coolsquid.squidapi.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.registry.Registry;
import com.github.coolsquid.squidapi.util.ModInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class BaseBlock extends Block {
	
	public static final Material material = new Material(MapColor.stoneColor);
			
	public BaseBlock(String name, CreativeTabs tab) {
		super(material);
		setBlockTextureName(ModInfo.modid + ":" + name);
		initBlock(name, tab);
	}
	
	public BaseBlock(String name, CreativeTabs tab, String textureName) {
		super(material);
		setBlockTextureName(ModInfo.modid + ":" + textureName);
		initBlock(name, tab);
	}
	
	public void initBlock(String name, CreativeTabs tab) {
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(tab);
		blockRegistry.register(this);
	}
	
	public static final Registry blockRegistry = new Registry();
	
	public static void dumpBlockNames() {
		Logger logger = new Logger("", "SquidBlocks");
		for (int a = 0; a < blockRegistry.size(); a++) {
			logger.log(((BaseBlock) blockRegistry.getObjectFromId(a)).getUnlocalizedName());
		}
		logger.save(false);
	}
}
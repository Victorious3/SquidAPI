package com.github.coolsquid.squidlib.block;

import com.github.coolsquid.squidlib.creativetab.SquidCreativeTabs;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block {
	
	public static final Material material = new Material(MapColor.stoneColor);
	
	public BaseBlock(String name, CreativeTabs tab) {
		super(material);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(name);
		setCreativeTab(tab);
		blockRegistry.register(this, name);
	}
	
	public static final Registry blockRegistry = new Registry();
	
	public static void dumpBlockNames() {
		Logger logger = new Logger("", "SquidBlocks");
		for (int a = 0; a < blockRegistry.size(); a++) {
			logger.log(((BaseBlock) blockRegistry.getObjectFromName(blockRegistry.getNameFromId(a))).getUnlocalizedName());
		}
		logger.save(false);
	}
}

package com.github.coolsquid.squidlib.block;

import com.github.coolsquid.squidlib.creativetab.SquidCreativeTabs;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block {

	public BaseBlock(String name) {
		super(new Material(MapColor.stoneColor));
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		setBlockTextureName(name);
		setCreativeTab(SquidCreativeTabs.squidTab);
		blockRegistry.register(this, name);
	}
	
	public static final Registry blockRegistry = new Registry();
	
	public static void dumpItemNames() {
		Logger logger = new Logger("", "SquidBlocks");
		for (int a = 0; a < blockRegistry.size(); a++) {
			logger.log(((BaseBlock) blockRegistry.getObjectFromName(blockRegistry.getNameFromId(a))).getUnlocalizedName());
		}
		logger.save(false);
	}
}
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
	}
}
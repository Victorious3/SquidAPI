package com.github.coolsquid.squidlib.item;

import net.minecraft.item.Item;

import com.github.coolsquid.squidlib.creativetab.SquidCreativeTabs;

import cpw.mods.fml.common.registry.GameRegistry;

public class BaseItem extends Item {
	
	public boolean isRepairable = false;
	
	public BaseItem(String name) {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(name);
		setCreativeTab(SquidCreativeTabs.squidTab);
	}
	
	public int getMaxStackSize() {
		return maxStackSize;
	}
	
	@Override
	public boolean isRepairable() {
		return isRepairable;
	}
}
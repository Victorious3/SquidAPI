/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.item;

import net.minecraft.item.Item;

import com.github.coolsquid.squidapi.registry.Registry;
import com.github.coolsquid.squidapi.util.ModInfo;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBasic extends Item {
	
	public static final Registry itemRegistry = new Registry();
	
	public boolean isRepairable = false;
	
	public ItemBasic(String name) {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(ModInfo.modid + ":" + name);
		itemRegistry.register(this);
	}
	
	public int getMaxStackSize() {
		return maxStackSize;
	}
	
	@Override
	public boolean isRepairable() {
		return isRepairable;
	}
	
	@Override
	public String toString() {
		return getUnlocalizedName();
	}
}
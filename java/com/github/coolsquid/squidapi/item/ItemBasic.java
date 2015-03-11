/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.item;

import net.minecraft.item.Item;

import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBasic extends Item {
	
	public boolean isRepairable = false;
	
	public ItemBasic(String name) {
		this.setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		this.setTextureName(Utils.newString(Utils.getCurrentMod().getModId(), ":", name));
	}
	
	public int getMaxStackSize() {
		return this.maxStackSize;
	}
	
	@Override
	public boolean isRepairable() {
		return this.isRepairable;
	}
}
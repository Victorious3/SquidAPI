/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.recipe;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class ExplosionRecipe {
	
	public static final HashMap<Object, ItemStack> recipes = new HashMap<Object, ItemStack>();
	
	public ExplosionRecipe(Object input, ItemStack output) {
		recipes.put(input, output);
	}
}
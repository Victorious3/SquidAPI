/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.coolsquid.squidapi.recipe.ExplosionRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper {
	
	public static void addShapelessRecipe(ItemStack output, Object[] params) {
		GameRegistry.addShapelessRecipe(output, params);
	}
	
	public static void addSmelting(Item input, ItemStack output) {
		GameRegistry.addSmelting(input, output, 10);
	}
	
	public static void addSmelting(Block input, ItemStack output) {
		GameRegistry.addSmelting(input, output, 10);	
	}
	
	public static void addExplosionRecipe(Object input, ItemStack output, float size) {
		ExplosionRecipe.recipes.put(input, new ExplosionRecipe(input, output, size));
	}
	
	public static void registerItem(Item item, String name) {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static void registerBlock(Block block, String name) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
}
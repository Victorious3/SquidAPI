/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import Reika.RotaryCraft.API.CompactorAPI;
import Reika.RotaryCraft.API.GrinderAPI;
import Reika.RotaryCraft.API.WorktableAPI;

public class RotaryCraftCompat {
	
	public static boolean loadCompat;
	
	public static void addGrindingRecipe(ItemStack input, ItemStack output) {
		GrinderAPI.addRecipe(input, output);
	}
	
	public static void addCompactorRecipe(ItemStack input, ItemStack output, int pressure, int temperature) {
		CompactorAPI.addCompactorRecipe(input, output, pressure, temperature);
	}
	
	public static void addShapedWorktableRecipe(ItemStack output, Object[] input) {
		WorktableAPI.addshapelessRecipe(output, input);
	}
	
	public static void addShapelessWorktableRecipe(ItemStack output, Object input) {
		WorktableAPI.addshapelessRecipe(output, input);
	}
}
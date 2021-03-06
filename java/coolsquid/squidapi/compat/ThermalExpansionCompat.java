/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import cofh.thermalexpansion.util.crafting.PulverizerManager;
import cofh.thermalexpansion.util.crafting.SawmillManager;

public class ThermalExpansionCompat {
	
	public static boolean loadCompat;
	
	public static void addPulverizerRecipe(ItemStack input, ItemStack primaryoutput, int energy) {
		PulverizerManager.addRecipe(energy, input, primaryoutput);
	}
	
	public static void addSawmillRecipe(ItemStack input, ItemStack output, int energy) {
		SawmillManager.addTERecipe(energy, input, output);
	}
}
package com.github.coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;

public class BloodMagicCompat {
	
	public static void addBloodAltarRecipe(ItemStack input, ItemStack output, int minTier, int liquidRequired, int consumptionRate, int drainRate, boolean canBeFilled) {
		AltarRecipeRegistry.registerAltarRecipe(output, input, minTier, liquidRequired, consumptionRate, drainRate, canBeFilled);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import mods.railcraft.api.crafting.RailcraftCraftingManager;

public class RailCraftCompat {
	
	public static void addBlastFurnaceRecipe(ItemStack input, ItemStack output, int cookTime) {
		RailcraftCraftingManager.blastFurnace.addRecipe(input, false, false, cookTime, output);
	}
	
	public static void addCokeOvenRecipe(ItemStack input, ItemStack output, FluidStack liquidOutput, int cookTime) {
		RailcraftCraftingManager.cokeOven.addRecipe(input, false, false, output, liquidOutput, cookTime);
	}
	
	public static void addRockCrusherRecipe(ItemStack input) {
		RailcraftCraftingManager.rockCrusher.createNewRecipe(input, false, false);
	}
	
	public static void addRollingMachineRecipe(ItemStack output, Object components) {
		RailcraftCraftingManager.rollingMachine.addRecipe(output, components);
	}
	
	public static void addShapelessRollingMachineRecipe(ItemStack output, Object components) {
		RailcraftCraftingManager.rollingMachine.addShapelessRecipe(output, components);
	}
}
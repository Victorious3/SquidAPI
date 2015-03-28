/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

public class BotaniaCompat {
	
	public static void registerManaInfusionRecipe(ItemStack output, Object input, int mana) {
		BotaniaAPI.registerManaInfusionRecipe(output, input, mana);
	}
	
	public static void registerManaAlchemyRecipe(ItemStack output, Object input, int mana) {
		BotaniaAPI.registerManaAlchemyRecipe(output, input, mana);
	}
	
	public static void registerManaConjurationRecipe(ItemStack output, Object input, int mana) {
		BotaniaAPI.registerManaConjurationRecipe(output, input, mana);
	}
	
	public static void registerElvenTradeRecipe(ItemStack output, Object... inputs) {
		BotaniaAPI.registerElvenTradeRecipe(output, inputs);
	}
	
	public static void registerPetalRecipe(ItemStack output, Object... inputs) {
		BotaniaAPI.registerPetalRecipe(output, inputs);
	}
}
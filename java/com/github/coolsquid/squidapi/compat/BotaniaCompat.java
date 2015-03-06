package com.github.coolsquid.squidapi.compat;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

import com.github.coolsquid.squidapi.helpers.LogHelper;

import cpw.mods.fml.common.API;
import cpw.mods.fml.common.Loader;

public class BotaniaCompat {
	
	
	public static boolean checkAPI() {
		if (!Loader.isModLoaded("Botania")) {
			return false;
		}
		String apiversion = Package.getPackage("vazkii.botania.api").getAnnotation(API.class).apiVersion();
		int version = Integer.parseInt(apiversion);
		if (version > 35) {
			LogHelper.warn("The version of Botania API loaded is newer than the version SquidUtils was made with. Problems may occur. Please contact CoolSquid.");
		}
		else if (version < 35) {
			LogHelper.warn("The version of Botania API loaded is older than the version SquidUtils was made with. Problems may occur. Please update Botania.");
		}
		return true;
	}
	
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
package com.github.coolsquid.squidlib;

import com.github.coolsquid.squidlib.handlers.CommonHandler;
import com.github.coolsquid.squidlib.handlers.RecipeRemover;
import com.github.coolsquid.squidlib.item.BaseItem;
import com.github.coolsquid.squidlib.util.ModInfo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class SquidAPI {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CommonHandler.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!RecipeRemover.recipesToRemove.isEmpty()) {
			RecipeRemover.removeRecipes();
		}
		BaseItem.dumpItemNames();
	}
}
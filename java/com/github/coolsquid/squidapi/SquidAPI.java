/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.RecipeRemover;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class SquidAPI {
	
	public static final Logger logger = new Logger("", "SquidAPI");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CommonHandler.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!RecipeRemover.recipesToRemove.isEmpty()) {
			logger.log("Recipes to remove:");
			RecipeRemover.recipesToRemove.dumpData(logger);
			RecipeRemover.removeRecipes();
		}
		logger.save(false);
	}
}
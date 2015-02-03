/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.command.CommandNews;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.RecipeRemover;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class SquidAPI {
	
	public static final Logger logger = new Logger("", "SquidAPI");
	
	static boolean isLocked;
	static boolean isOffline;
	
	/**
	 * @return the isLocked
	 */
	public static boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public static void setLocked(boolean isLocked) {
		SquidAPI.isLocked = isLocked;
	}
	
	/**
	 * @return the isOffline
	 */
	public static boolean isOffline() {
		return isOffline;
	}

	/**
	 * @param isOffline the isOffline to set
	 */
	public static void setOffline(boolean isOffline) {
		SquidAPI.isOffline = isOffline;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SquidAPIAuthentificationHelper.auth(ModInfo.modid, ModInfo.version, "http://pastebin.com/raw.php?i=JpPZeb0q");
		CommonHandler.init();
		if (isLocked()) {
			return;
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (isLocked()) {
			LogHelper.warn("**********************************************************************************************************************************************************************");
			LogHelper.warn("Authentification failed! The mod will not load. This happens because the mod might have been obtained trough an illegal site. Read more at http://stopmodreposts.org/.");
			LogHelper.warn("Download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			LogHelper.warn("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				LogHelper.warn((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			LogHelper.warn("Minecraft will now exit.");
			LogHelper.warn("**********************************************************************************************************************************************************************");
			logger.log("**********************************************************************************************************************************************************************");
			logger.log("Authentification failed! The mod will not load. This happens because the mod might have been obtained trough an illegal site. Read more at http://stopmodreposts.org/.");
			logger.log("Download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			logger.log("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				logger.log((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			logger.log("Minecraft will now exit.");
			logger.log("**********************************************************************************************************************************************************************");
			logger.save(false);
			throw new SecurityException("Authentification failed!\nThis happens because the mod might have been obtained trough an illegal site. A list of known illegal sites may be found at http://stopmodreposts.org/.\nDownload the **latest** official version from: http://www.curse.com/mc-mods/minecraft/227345-squidapi/download.");
		}
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		if (Utils.developmentEnvironment) {
			MinecraftForge.EVENT_BUS.register(new DevEnvironmentEventHandler());
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (isLocked()) {
			return;
		}
		if (!RecipeRemover.recipesToRemove.isEmpty()) {
			logger.log("Recipes to remove:");
			RecipeRemover.recipesToRemove.dumpData(logger);
			RecipeRemover.removeRecipes();
		}
		logger.save(false);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		if (isLocked()) {
			return;
		}
		event.registerServerCommand(new CommandNews());
	}
}
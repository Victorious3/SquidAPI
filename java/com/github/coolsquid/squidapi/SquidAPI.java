/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.command.CommandNews;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.helpers.SquidAPIAuthentificationHelper;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.RecipeRemover;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class SquidAPI {
	
	public static final Logger logger = new Logger("", "SquidAPI");
	
	public static boolean isLocked;
	public static boolean isOffline;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SquidAPIAuthentificationHelper.auth(ModInfo.modid, ModInfo.version, "http://pastebin.com/raw.php?i=JpPZeb0q");
		CommonHandler.init();
		isLocked = true;
		if (isLocked) {
			FMLCommonHandler.instance().bus().register(this);
			LogHelper.info("**********************************************************************************************************************************************************************");
			LogHelper.warn("Authentification failed! The mod will not load. This happens because the mod might have been obtained trough an illegal site. Read more at http://stopmodreposts.org/.");
			LogHelper.warn("Download the latest official version from: http://www.curse.com/mc-mods/minecraft/227345-squidapi/download.");
			LogHelper.warn("Stacktrace:");
			StackTraceElement[] stacktrace = new Throwable().getStackTrace();
			for (int a = 0; a < stacktrace.length; a++) {
				LogHelper.warn(stacktrace[a] + "");
			}
			LogHelper.info("**********************************************************************************************************************************************************************");
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (isLocked) {
			return;
		}
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		if (Utils.developmentEnvironment) {
			MinecraftForge.EVENT_BUS.register(new DevEnvironmentEventHandler());
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (isLocked) {
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
		if (isLocked) {
			return;
		}
		event.registerServerCommand(new CommandNews());
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (Utils.isClient()) {
			event.player.addChatMessage(new ChatComponentText("§4<SquidAPI>§r SquidAPI and its dependants have §4not§r §4been§3 §4activated§r. This is because the mod might have been downloaded from an §4illegal§r §4redistributor§r. Please download the §4latest§r version from §6Curse§r."));
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.auth.AuthEntry;
import com.github.coolsquid.squidapi.auth.Authentificator;
import com.github.coolsquid.squidapi.auth.SquidAPIAuthentificationHelper;
import com.github.coolsquid.squidapi.command.CommandNews;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
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
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version, acceptableRemoteVersions = "*")
public class SquidAPI {
	
	public static final Logger logger = new Logger("", "SquidAPI");

	public static boolean isLocked() {
		return !SquidAPIAuthentificationHelper.unauthorisedmods.isEmpty();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SquidAPIAuthentificationHelper.auth(new AuthEntry(ModInfo.modid, ModInfo.version, "http://pastebin.com/raw.php?i=JpPZeb0q"));
		CommonHandler.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (!SquidAPIAuthentificationHelper.modstocheck.isEmpty()) {
			new Authentificator().start();
		}
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExplosionRecipeHandler());
		if (Utils.developmentEnvironment) {
			MinecraftForge.EVENT_BUS.register(new DevEnvironmentEventHandler());
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!RecipeRemover.recipesToRemove.isEmpty()) {
			logger.log("Recipes to remove:");
			RecipeRemover.recipesToRemove.dumpData(logger);
			RecipeRemover.removeRecipes();
		}
		logger.save();
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandNews());
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if (Utils.isClient()) {
			event.player.addChatMessage(new ChatComponentText("§4<SquidAPI>§r SquidAPI has detected mods downloaded through an illegal website. Please download the §4latest§r version from §6my§r §6website§r to remove this message. A list of unauthorised mods may be found in SquidAPI.log."));
		}
	}
}
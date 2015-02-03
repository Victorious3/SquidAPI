/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.command.CommandNews;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.recipe.ExplosionRecipe;
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
	
	private static boolean isLocked;
	private static boolean isOffline;
	
	/**
	 * @return the isLocked
	 */
	public static boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public static void setLocked() {
		SquidAPI.isLocked = true;
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
	public static void setOffline() {
		SquidAPI.isOffline = true;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SquidAPIAuthentificationHelper.auth(ModInfo.modid, ModInfo.version, "http://pastebin.com/raw.php?i=JpPZeb0q");
		CommonHandler.init();
	}
	
	public static String n = System.getProperty("line.separator");
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (isLocked()) {
			Font font = new Font("Consolas", Font.BOLD, 20);
			JFrame frame = new JFrame();
			JTextPane text = new JTextPane();
			text.setFont(font);
			text.setText("SquidAPI has detected mods downloaded through an illegal website." + n + "To fix this error, download the **latest** official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			text.setVisible(true);
			text.setEditable(false);
			frame.setTitle("ILLEGAL MOD DISTRIBUTION DETECTED!");
			frame.setSize(800, 600);
			frame.add(text);
			frame.setVisible(true);
			FMLCommonHandler.instance().bus().register(this);
			LogHelper.warn("**********************************************************************************************************************************************************************");
			LogHelper.warn("SquidAPI has detected mods downloaded through an illegal website.");
			LogHelper.warn("To fix this error, download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			LogHelper.warn("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				LogHelper.warn((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			LogHelper.warn("**********************************************************************************************************************************************************************");
			logger.log("**********************************************************************************************************************************************************************");
			logger.log("SquidAPI has detected mods downloaded through an illegal website.");
			logger.log("To fix this error, download the latest official version from: http://coolsquid.wix.com/software#!downloads/cppf.");
			logger.log("Unauthorised mods:");
			for (int a = 0; a < SquidAPIAuthentificationHelper.unauthorisedmods.size(); a++) {
				logger.log((String) SquidAPIAuthentificationHelper.unauthorisedmods.get(a));
			}
			logger.log("**********************************************************************************************************************************************************************");
		}
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExplosionRecipe());
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
		logger.save(false);
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
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.command.ICommand;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;

import com.github.coolsquid.squidapi.command.CommandSquidAPI;
import com.github.coolsquid.squidapi.config.ConfigHandler;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.util.ContentRemover;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version, acceptableRemoteVersions = "*")
public class SquidAPI extends SquidAPIMod {
	
	public SquidAPI() {
		super("An API for all my mods.");
	}
	
	public static final Logger logger = new Logger("./logs", "./logs/SquidAPI.log");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Preinitializing.");
		
		CommonHandler.init();
		
		new ConfigHandler(event.getSuggestedConfigurationFile()).preInit();
		
		if (Utils.developmentEnvironment || ConfigHandler.cleanMenu) {
			ReflectionHelper.in(ForgeVersion.class).field("status", "status").set(Status.UP_TO_DATE);
			ReflectionHelper.in(FMLCommonHandler.class).field("brandings", "brandings").set(FMLCommonHandler.instance(), Utils.newList());
			ReflectionHelper.in(FMLCommonHandler.class).field("brandingsNoMC", "brandingsNoMC").set(FMLCommonHandler.instance(), Utils.newList());
		}
		if (!ConfigHandler.branding.equals("")) {
			ReflectionHelper.in(FMLCommonHandler.class).field("brandings", "brandings").set(FMLCommonHandler.instance(), Utils.newList(ConfigHandler.branding));
			ReflectionHelper.in(FMLCommonHandler.class).field("brandingsNoMC", "brandingsNoMC").set(FMLCommonHandler.instance(), Utils.newList(ConfigHandler.branding));
		}
		
		if (!Loader.isModLoaded("DragonAPI") && ConfigHandler.maxPotionId != 32) {
			LogHelper.info("Setting the max potion id to ", ConfigHandler.maxPotionId, ".");
			ReflectionHelper.in(Potion.class).finalField("potionTypes", "field_76425_a").set(Arrays.copyOf(Potion.potionTypes, ConfigHandler.maxPotionId));
		}
		
		ContentRemover.blacklist("RotaryCraft", "ReactorCraft", "ElectriCraft", "ChromatiCraft");
		
		LogHelper.info("Finished preinitialization.");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		LogHelper.info("Initializing.");
		
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExplosionRecipeHandler());
		if (Utils.developmentEnvironment) {
			MinecraftForge.EVENT_BUS.register(new DevEnvironmentEventHandler());
		}
		NBTTagCompound nbttag = new NBTTagCompound();
		nbttag.setString("curseProjectName", "227345-squidapi");
		nbttag.setString("curseFilenameParser", ModInfo.modid + "-[].jar");
		FMLInterModComms.sendRuntimeMessage(ModInfo.modid, "VersionChecker", "addCurseCheck", nbttag);
		
		LogHelper.info("Finished initialization.");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Postinitializing.");
		LogHelper.info("Finished postinitialization.");
	}
	
	@EventHandler
	public void finishedLoading(FMLLoadCompleteEvent event) {
		ContentRemover.removeContent();
	}
	
	public static final ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandSquidAPI());
		for (ICommand a: commands) {
			event.registerServerCommand(a);
		}
	}
}
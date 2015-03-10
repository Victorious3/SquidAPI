/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

import com.github.coolsquid.squidapi.command.CommandAbout;
import com.github.coolsquid.squidapi.command.CommandDisable;
import com.github.coolsquid.squidapi.command.CommandEnable;
import com.github.coolsquid.squidapi.command.CommandSquidAPI;
import com.github.coolsquid.squidapi.config.ConfigHandler;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.IdHelper;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.helpers.OreDictionaryHelper;
import com.github.coolsquid.squidapi.helpers.VillageHelper;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.util.ContentRemover;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.ShutdownHandler;
import com.github.coolsquid.squidapi.util.ShutdownHandler.ShutdownEvent;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version, dependencies = ModInfo.dependencies, acceptableRemoteVersions = "*")
public class SquidAPI extends SquidAPIMod {

	public SquidAPI() {
		super("An API for all my mods.");
	}
	
	@Instance
	private static SquidAPI instance;
	
	private static Mod mod;
	
	public static SquidAPI instance() {
		return instance;
	}
	
	public static Mod getMod() {
		if (mod == null) {
			mod = ReflectionHelper.in(SquidAPI.class).getAnnotation(Mod.class);
		}
		return mod;
	}
	
	public static final Logger logger = new Logger("./logs", "./logs/SquidAPI.log");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Preinitializing.");
		
		CommonHandler.init();
		
		Runtime.getRuntime().addShutdownHook(new ShutdownHandler());
		
		new ConfigHandler(event.getSuggestedConfigurationFile()).preInit();
		
		ModMetadata mcmeta = Loader.instance().getMinecraftModContainer().getMetadata();
		mcmeta.autogenerated = false;
		mcmeta.url = "https://minecraft.net/";
		mcmeta.authorList.add("Mojang");
		mcmeta.description = "A game about breaking and placing blocks.";
		
		if (Utils.developmentEnvironment() || ConfigHandler.cleanMenu) {
			ReflectionHelper.in(ForgeVersion.class).field("status", "status").set(Status.UP_TO_DATE);
			ReflectionHelper.in(FMLCommonHandler.class).field("brandings", "brandings").set(FMLCommonHandler.instance(), Lists.newArrayList());
			ReflectionHelper.in(FMLCommonHandler.class).field("brandingsNoMC", "brandingsNoMC").set(FMLCommonHandler.instance(), Lists.newArrayList());
		}
		if (!ConfigHandler.branding.equals("")) {
			ReflectionHelper.in(FMLCommonHandler.class).field("brandings", "brandings").set(FMLCommonHandler.instance(), Lists.newArrayList(ConfigHandler.branding));
			ReflectionHelper.in(FMLCommonHandler.class).field("brandingsNoMC", "brandingsNoMC").set(FMLCommonHandler.instance(), Lists.newArrayList(ConfigHandler.branding));
		}
		
		if (!Loader.isModLoaded("DragonAPI") && ConfigHandler.maxPotionId != 32) {
			LogHelper.info("Setting the max potion id to ", ConfigHandler.maxPotionId, ".");
			Potion.potionTypes = Arrays.copyOf(Potion.potionTypes, ConfigHandler.maxPotionId);
		}
		
		if (Loader.isModLoaded("MineFactoryReloaded|CompatIC2")) {
			MinecraftForge.EVENT_BUS.register(this);
		}
		
		LogHelper.info("Finished preinitialization.");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		LogHelper.info("Initializing.");
		
		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExplosionRecipeHandler());
		if (Utils.developmentEnvironment()) {
			MinecraftForge.EVENT_BUS.register(new DevEnvironmentEventHandler());
		}
		
		Utils.runVersionCheckerCompat("227345");
		
		LogHelper.info("Finished initialization.");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Postinitializing.");
		IdHelper.saveIds();
		LogHelper.info("Finished postinitialization.");
	}
	
	@EventHandler
	public void finishedLoading(FMLLoadCompleteEvent event) {
		ContentRemover.removeContent();
		if (!VillageHelper.professionstoremove.isEmpty()) {
			MinecraftForge.EVENT_BUS.register(new VillageHelper());
		}
	}
	
	public static final ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandSquidAPI());
		event.registerServerCommand(new CommandDisable());
		event.registerServerCommand(new CommandEnable());
		ClientCommandHandler.instance.registerCommand(new CommandAbout());
		for (ICommand a: commands) {
			event.registerServerCommand(a);
		}
	}
	
	public static final List<String> messages = Lists.newArrayList();
	
	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {
		for (String message: messages) {
			event.player.addChatMessage(new ChatMessage("<SquidAPI> ").setColor(EnumChatFormatting.RED).appendSibling(new ChatMessage(message)));
		}
	}
	
	@SubscribeEvent
	public void onOredictRegistration(OreRegisterEvent event) {
		if (event.Name.equals("greggy_greg_do_please_kindly_stuff_a_sock_in_it")) {
			OreDictionaryHelper.removeEntry("greggy_greg_do_please_kindly_stuff_a_sock_in_it");
		}
	}
	
	@SubscribeEvent
	public void onShutdown(ShutdownEvent event) {
		logger.save();
	}
}
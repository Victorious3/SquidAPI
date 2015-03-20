/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
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
import com.github.coolsquid.squidapi.command.CommandSuggest;
import com.github.coolsquid.squidapi.config.ConfigHandler;
import com.github.coolsquid.squidapi.handlers.CommonHandler;
import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import com.github.coolsquid.squidapi.handlers.ModEventHandler;
import com.github.coolsquid.squidapi.helpers.IdHelper;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.helpers.OreDictionaryHelper;
import com.github.coolsquid.squidapi.helpers.VillageHelper;
import com.github.coolsquid.squidapi.helpers.server.ServerHelper;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.registry.DamageSourceRegistry;
import com.github.coolsquid.squidapi.registry.VanillaBlockRegistry;
import com.github.coolsquid.squidapi.registry.VanillaItemRegistry;
import com.github.coolsquid.squidapi.registry.WorldTypeRegistry;
import com.github.coolsquid.squidapi.util.Charsets;
import com.github.coolsquid.squidapi.util.ContentRemover;
import com.github.coolsquid.squidapi.util.ModInfo;
import com.github.coolsquid.squidapi.util.ShutdownHandler;
import com.github.coolsquid.squidapi.util.ShutdownHandler.ShutdownEvent;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.ImmutableSet;
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

	public static SquidAPI instance() {
		return instance;
	}
	
	public static final Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Preinitializing.");
		
		VanillaBlockRegistry.instance();
		VanillaItemRegistry.instance();
		
		CommonHandler.instance().init();
		
		Runtime.getRuntime().addShutdownHook(new ShutdownHandler());
		
		new ConfigHandler(event.getSuggestedConfigurationFile()).preInit();
		
		ModMetadata mcmeta = Loader.instance().getMinecraftModContainer().getMetadata();
		mcmeta.autogenerated = false;
		mcmeta.url = "https://minecraft.net/";
		mcmeta.authorList.add("Mojang");
		mcmeta.description = "A game about breaking and placing blocks.";
		
		if (Utils.developmentEnvironment() || ConfigHandler.cleanMenu) {
			ReflectionHelper.in(ForgeVersion.class).field("status", "status").set(Status.UP_TO_DATE);
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandings", "brandings").set(Lists.newArrayList());
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandingsNoMC", "brandingsNoMC").set(Lists.newArrayList());
		}
		if (!ConfigHandler.branding.equals("")) {
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandings", "brandings").set(Lists.newArrayList(ConfigHandler.branding));
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandingsNoMC", "brandingsNoMC").set(Lists.newArrayList(ConfigHandler.branding));
		}

		if (!Loader.isModLoaded("DragonAPI")) {
			LogHelper.info("Setting the max potion id to 256.");
			Potion.potionTypes = Arrays.copyOf(Potion.potionTypes, 256);
		}

		if (Loader.isModLoaded("MineFactoryReloaded|CompatIC2")) {
			MinecraftForge.EVENT_BUS.register(this);
		}

		for (SquidAPIMod mod: SquidAPIMod.getMods()) {
			mod.preInit();
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

		this.suggestMod("SquidUtils", "It provides the user with many customization options, from disabling mobs to creating new biomes.", "http://bit.ly/1EB3Y5N");
		this.suggestMod("StarStones", "Meteors!", "http://bit.ly/1EB3Y5N");
		this.suggestMod("FighterMobs", "Gives abilities to certain Vanilla mobs!", "http://bit.ly/1EB3Y5N");
		this.suggestMod("SafeChat", "Filters swearwords from the chat. Perfect for family servers!", "http://bit.ly/1EB3Y5N");

		Utils.runVersionCheckerCompat("227345");

		for (SquidAPIMod mod: SquidAPIMod.getMods()) {
			mod.init();
		}

		LogHelper.info("Finished initialization.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Postinitializing.");
		
		ContentRemover.removeContent();
		IdHelper.saveIds();
		IdHelper.checkForConflicts();

		WorldTypeRegistry.instance();
		DamageSourceRegistry.instance();

		for (SquidAPIMod mod: SquidAPIMod.getMods()) {
			mod.postInit();
		}

		LogHelper.info("Finished postinitialization.");
	}

	@EventHandler
	public void finishedLoading(FMLLoadCompleteEvent event) {
		if (!VillageHelper.professionstoremove.isEmpty()) {
			MinecraftForge.EVENT_BUS.register(new VillageHelper());
		}
	}

	public static final ArrayList<ICommand> commands = new ArrayList<ICommand>();

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		if (Utils.isClient()) {
			this.registerServerCommand(new CommandDisable());
			this.registerServerCommand(new CommandEnable());
			this.registerClientCommand(new CommandAbout());
			this.registerClientCommand(new CommandSquidAPI());
			this.registerClientCommand(new CommandSuggest());
		}
		for (ICommand a: commands) {
			this.registerServerCommand(a);
		}
	}

	public static final List<String> messages = Lists.newArrayList();

	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {
		for (String message: messages) {
			event.player.addChatMessage(new ChatMessage("<SquidAPI> ").setColor(EnumChatFormatting.RED).appendSibling(new ChatMessage(message)));
		}
		Minecraft.getMinecraft().gameSettings.saturation = 0.5F;
	}

	private static final Set<String> oredictEntriesToRemove = ImmutableSet.of("greggy_greg_do_please_kindly_stuff_a_sock_in_it");

	@SubscribeEvent
	public void onOredictRegistration(OreRegisterEvent event) {
		if (oredictEntriesToRemove.contains(event.Name)) {
			OreDictionaryHelper.removeEntry(event.Name);
		}
		else if (Charsets.isRandomLetters(event.Name)) {
			LogHelper.info("Ordictionary entry ", event.Name, " is very long, and seems to consist of random letters.");
		}
	}

	@SubscribeEvent
	public void onShutdown(ShutdownEvent event) {
		if (Utils.getChance(1, 10)) {
			LogHelper.info("Have a nice day!");
		}
	}

	public static void registerCommands(ICommand... commands) {
		for (ICommand command: commands) {
			SquidAPI.commands.add(command);
		}
	}

	private void registerClientCommand(ICommand command) {
		ClientCommandHandler.instance.registerCommand(command);
		LogHelper.info("Registering clientside command ", command.getCommandName(), ".");
	}

	private void registerServerCommand(ICommand command) {
		ServerHelper.registerCommand(command.getCommandName(), command);
		LogHelper.info("Registering serverside command ", command.getCommandName(), ".");
	}

	static {
		File file = new File("./logs/SquidAPI.log");
		if (file.exists()) {
			file.delete();
		}
		logger = new Logger(file);
	}
}
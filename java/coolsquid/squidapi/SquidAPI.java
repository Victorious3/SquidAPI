/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.minecraft.command.ICommand;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import coolsquid.squidapi.command.CommandAbout;
import coolsquid.squidapi.command.CommandDisable;
import coolsquid.squidapi.command.CommandEnable;
import coolsquid.squidapi.command.CommandSquidAPI;
import coolsquid.squidapi.command.CommandSuggest;
import coolsquid.squidapi.config.ConfigHandler;
import coolsquid.squidapi.config.SquidAPIConfig;
import coolsquid.squidapi.handlers.CommonHandler;
import coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import coolsquid.squidapi.handlers.ModEventHandler;
import coolsquid.squidapi.handlers.MonetizationHandler;
import coolsquid.squidapi.helpers.IdHelper;
import coolsquid.squidapi.helpers.LogHelper;
import coolsquid.squidapi.helpers.OreDictionaryHelper;
import coolsquid.squidapi.helpers.VillageHelper;
import coolsquid.squidapi.helpers.server.ServerHelper;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.logging.Logger;
import coolsquid.squidapi.reflection.ReflectionHelper;
import coolsquid.squidapi.registry.DamageSourceRegistry;
import coolsquid.squidapi.registry.VanillaBlockRegistry;
import coolsquid.squidapi.registry.VanillaItemRegistry;
import coolsquid.squidapi.registry.WorldTypeRegistry;
import coolsquid.squidapi.util.ContentRemover;
import coolsquid.squidapi.util.ModInfo;
import coolsquid.squidapi.util.ShutdownHandler;
import coolsquid.squidapi.util.Utils;
import coolsquid.squidapi.util.ShutdownHandler.ShutdownEvent;
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

	private final SquidAPIConfig commandConfig = new SquidAPIConfig(new File("./config/SquidAPI/commands.cfg"));

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Preinitializing.");
		LogHelper.info("Version id: ", this.hashCode());

		VanillaBlockRegistry.instance();
		VanillaItemRegistry.instance();

		CommonHandler.instance().init();

		Runtime.getRuntime().addShutdownHook(new ShutdownHandler());

		new ConfigHandler(event.getSuggestedConfigurationFile()).preInit();
		this.commandConfig.addHeader("//Disable any commands by setting their value to false.");

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

		for (SquidAPIMod mod: getMods()) {
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
		if (!Utils.isClient()) {
			MinecraftForge.EVENT_BUS.register(new MonetizationHandler(SquidAPIMod.getModids()));
		}

		this.suggestMod("SquidUtils", "It provides the user with many customization options, from disabling mobs to creating new biomes.", "http://bit.ly/1EB3Y5N");
		this.suggestMod("StarStones", "Meteors!", "http://bit.ly/1EB3Y5N");
		this.suggestMod("FighterMobs", "Gives abilities to certain Vanilla mobs!", "http://bit.ly/1EB3Y5N");
		this.suggestMod("SafeChat", "Filters swearwords from the chat. Perfect for family servers!", "http://bit.ly/1EB3Y5N");

		Utils.runVersionCheckerCompat("227345");

		for (SquidAPIMod mod: getMods()) {
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

		for (SquidAPIMod mod: getMods()) {
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
			this.registerClientCommand(new CommandDisable());
			this.registerClientCommand(new CommandEnable());
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
	}

	private final Set<String> oredictEntriesToRemove = ImmutableSet.of("greggy_greg_do_please_kindly_stuff_a_sock_in_it");

	@SubscribeEvent
	public void onOredictRegistration(OreRegisterEvent event) {
		if (this.oredictEntriesToRemove.contains(event.Name)) {
			OreDictionaryHelper.removeEntry(event.Name);
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
		String name = command.getCommandName();
		if (this.commandConfig.get(name, true)) {
			ClientCommandHandler.instance.registerCommand(command);
			LogHelper.info("Registering clientside command ", name, ".");
		}
	}

	private void registerServerCommand(ICommand command) {
		String name = command.getCommandName();
		if (name != null && this.commandConfig.get(name, true)) {
			ServerHelper.registerCommand(name, command);
			LogHelper.info("Registering serverside command ", name, ".");
		}
	}

	static {
		File file = new File("./logs/SquidAPI.log");
		if (file.exists()) {
			file.delete();
		}
		logger = new Logger(file);
	}
}
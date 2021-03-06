/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.command.ICommand;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.ForgeVersion.Status;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.core.Logger;

import com.google.common.collect.Lists;

import coolsquid.squidapi.asm.SquidAPIPlugin;
import coolsquid.squidapi.command.CommandAbout;
import coolsquid.squidapi.command.CommandDisable;
import coolsquid.squidapi.command.CommandEnable;
import coolsquid.squidapi.command.CommandLightningStrike;
import coolsquid.squidapi.command.CommandSquidAPI;
import coolsquid.squidapi.config.ModConfigHandler;
import coolsquid.squidapi.handlers.CommonHandler;
import coolsquid.squidapi.handlers.ExplosionRecipeHandler;
import coolsquid.squidapi.handlers.ModEventHandler;
import coolsquid.squidapi.handlers.MonetizationHandler;
import coolsquid.squidapi.handlers.ShutdownHandler;
import coolsquid.squidapi.helpers.IdHelper;
import coolsquid.squidapi.helpers.VillageHelper;
import coolsquid.squidapi.helpers.server.ServerHelper;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.reflection.ReflectionHelper;
import coolsquid.squidapi.util.ContentRemover;
import coolsquid.squidapi.util.EasterEggUtils;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.ModInfo;
import coolsquid.squidapi.util.ModManager;
import coolsquid.squidapi.util.RewardManager;
import coolsquid.squidapi.util.Utils;
import coolsquid.squidapi.util.collect.VanillaBlockRegistry;
import coolsquid.squidapi.util.collect.VanillaItemRegistry;
import coolsquid.squidapi.util.objects.TextureMapLogger;
import coolsquid.squidapi.util.version.UpdateManager;
import coolsquid.squidapi.util.version.UpdaterAPI;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version, dependencies = ModInfo.dependencies, acceptableRemoteVersions = "*")
public class SquidAPI extends SquidAPIMod {

	public static final CommonHandler COMMON = new CommonHandler();
	public static final UpdaterAPI UPDATER = new UpdateManager();

	public SquidAPI() {
		super("An API for all my mods.", 227345);
		this.setUpdateUrl("http://pastebin.com/raw.php?i=rPYGL0rZ");
	}

	@Instance
	private static SquidAPI instance;

	public static SquidAPI instance() {
		return instance;
	}

	private final Configuration commandConfig = new Configuration(new File("./config/SquidAPI/commands.cfg"));

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.getTimer().startTiming();
		this.info("Preinitializing.");
		this.info("Version id: " + this.hashCode() + '.');
		this.info("File hash: " + SquidAPIPlugin.getHash() + '.');

		VanillaBlockRegistry.instance();
		VanillaItemRegistry.instance();

		COMMON.init();

		Runtime.getRuntime().addShutdownHook(new ShutdownHandler());

		ModConfigHandler.INSTANCE.init();

		ModMetadata mcmeta = Loader.instance().getMinecraftModContainer().getMetadata();
		mcmeta.autogenerated = false;
		mcmeta.url = "https://minecraft.net/";
		mcmeta.authorList.add("Mojang");
		mcmeta.description = "A game about breaking and placing blocks.";

		if (MiscLib.DEV_ENVIRONMENT || MiscLib.SETTINGS.getBoolean("cleanMenu")) {
			ReflectionHelper.in(ForgeVersion.class).field("status", "status").set(Status.UP_TO_DATE);
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandings", "brandings").set(Lists.newArrayList());
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandingsNoMC", "brandingsNoMC").set(Lists.newArrayList());
		}
		String branding = MiscLib.SETTINGS.getProperty("branding");
		if (!branding.equals("")) {
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandings", "brandings").set(Lists.newArrayList(branding));
			ReflectionHelper.in(FMLCommonHandler.instance()).field("brandingsNoMC", "brandingsNoMC").set(Lists.newArrayList(branding));
		}

		if (Potion.potionTypes.length == 32) {
			this.info("Changing the max potion id.");
			Potion.potionTypes = Arrays.copyOf(Potion.potionTypes, 128);
		}

		if (MiscLib.CLIENT && MiscLib.SETTINGS.getBoolean("cleanUpTextureErrors")) {
			TextureMap.logger = new TextureMapLogger((Logger) TextureMap.logger);
		}

		for (SquidAPIMod mod: ModManager.INSTANCE.getMods()) {
			mod.preInit();
		}

		MiscLib.LOGGER.info("Preinitialization took " + this.getTimer().stopTiming() + "ms.");
		this.info("Finished preinitialization.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.getTimer().startTiming();
		this.info("Initializing.");

		FMLCommonHandler.instance().bus().register(this);
		Object handler = new ModEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
		MinecraftForge.EVENT_BUS.register(new ExplosionRecipeHandler());
		if (MiscLib.SERVER) {
			MinecraftForge.EVENT_BUS.register(new MonetizationHandler(ModManager.INSTANCE.getModids()));
		}

		for (SquidAPIMod mod: ModManager.INSTANCE.getMods()) {
			mod.init();
		}

		if (EasterEggUtils.APRIL_FOOLS) {
			List<String> descs = Lists.newArrayList();
			for (ModContainer mod: Loader.instance().getModList()) {
				String desc = mod.getMetadata().description;
				if (desc != null) {
					descs.add(desc);
				}
			}
			for (ModContainer mod: Loader.instance().getModList()) {
				mod.getMetadata().description = descs.remove(Utils.getRandInt(0, descs.size() - 1));
			}
		}
		else if (EasterEggUtils.EASTER) {
			this.getMetadata().credits = "The Easter Bunny <3";
		}
		else if (EasterEggUtils.HALLOWEEN) {
			this.info("Happy halloween... >:)");
		}

		MiscLib.LOGGER.info("Initialization took " + this.getTimer().stopTiming() + "ms.");
		this.info("Finished initialization.");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.getTimer().startTiming();
		this.info("Postinitializing.");

		((UpdateManager) UPDATER).start();

		ContentRemover.removeContent();
		IdHelper.saveIds();
		IdHelper.checkForConflicts();

		for (SquidAPIMod mod: ModManager.INSTANCE.getMods()) {
			mod.postInit();
		}

		MiscLib.LOGGER.info("Postinitialization took " + this.getTimer().stopTiming() + "ms.");
		this.info("Finished postinitialization.");
	}

	@EventHandler
	public void finishedLoading(FMLLoadCompleteEvent event) {
		if (!VillageHelper.professionstoremove.isEmpty()) {
			MinecraftForge.EVENT_BUS.register(new VillageHelper());
		}
		if (MiscLib.CLIENT) {
			this.registerClientCommand(new CommandDisable());
			this.registerClientCommand(new CommandEnable());
			this.registerClientCommand(new CommandAbout());
			this.registerClientCommand(CommandSquidAPI.INSTANCE);
		}
	}

	public final ArrayList<ICommand> commands = new ArrayList<ICommand>();

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		for (ICommand a: this.commands) {
			this.registerServerCommand(a);
		}
		if (this.commandConfig.hasChanged()) {
			this.commandConfig.save();
		}
	}

	@Deprecated
	public final List<Object> messages = Lists.newArrayList();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {
		for (Object message: this.messages) {
			if (message instanceof String) {
				event.player.addChatMessage(new ChatMessage("<SquidAPI> ").setColor(EnumChatFormatting.RED).appendSibling(new ChatMessage((String) message)));
			}
			else if (message instanceof IChatComponent) {
				event.player.addChatMessage((IChatComponent) message);
			}
		}
		if (MiscLib.DEV_ENVIRONMENT || RewardManager.INSTANCE.isPatreon(event.player.getGameProfile().getId())) {
			this.registerClientCommand(new CommandLightningStrike());
		}
		if (this.commandConfig.hasChanged()) {
			this.commandConfig.save();
		}
	}

	public void registerCommands(ICommand... commands) {
		for (ICommand command: commands) {
			this.commands.add(command);
		}
	}

	private void registerClientCommand(ICommand command) {
		String name = command.getCommandName();
		if (this.commandConfig.get(name, "enabled", true).getBoolean()) {
			ClientCommandHandler.instance.registerCommand(command);
			this.info("Registering clientside command " + name + '.');
		}
	}

	private void registerServerCommand(ICommand command) {
		String name = command.getCommandName();
		if (name != null && this.commandConfig.get(name, "enabled", true).getBoolean()) {
			ServerHelper.registerCommand(command);
			this.info("Registering serverside command " + name + '.');
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Maps;

import coolsquid.squidapi.command.CommandSquidAPI;
import coolsquid.squidapi.command.ISubCommand;
import coolsquid.squidapi.config.ModConfigHandler;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.ModManager;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class UpdateManager {

	public static final UpdateManager INSTANCE = new UpdateManager();

	final Map<String, VersionContainer> outdatedMods = Maps.newHashMap();
	private boolean enabled = MiscLib.updateChecker();

	private UpdateManager() {
		CommandSquidAPI.INSTANCE.registerSubcommand(new SubcommandUpdateChecker());
		if (this.enabled) {
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

	public void check(IUpdateable mod) {
		if (mod.getCurseUrl() != null && this.enabled) {
			this.getUpdateChecker(mod).start();
		}
	}

	private UpdateChecker getUpdateChecker(IUpdateable mod) {
		return new UpdateChecker(mod);
	}

	void markAsOutdated(IUpdateable mod, String newVersion) {
		this.outdatedMods.put(mod.getModid(), new VersionContainer(mod.getName(), mod.getCurseUrl(), mod.getVersion(), newVersion));
	}

	void disable() {
		this.enabled = false;
	}

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onGuiInit(InitGuiEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			GuiButton button = new GuiButton(10, 10, 0, "Updates") {
				@Override
				public void mouseReleased(int p_146118_1_, int p_146118_2_) {
					GuiScreen screen = new GuiUpdates();
					Minecraft.getMinecraft().displayGuiScreen(screen);
				}
			};
			button.yPosition = event.gui.height - button.height - 10;
			button.width = 50;
			event.buttonList.add(button);
		}
	}

	private class SubcommandUpdateChecker implements ISubCommand {

		@Override
		public String getName() {
			return "updatechecker";
		}

		@Override
		public void execute(ICommandSender sender, List<String> args) {
			if (args.get(0).equals("disable")) {
				ModConfigHandler.INSTANCE.setProperty(ModConfigHandler.CATEGORY_GENERAL, "updateChecker", "Enables the update checker.", false);
			}
			else if (args.get(0).equals("getStatus")) {
				VersionContainer v = UpdateManager.this.outdatedMods.get(args.get(1));
				if (!ModManager.INSTANCE.getModids().contains(args.get(1))) {
					sender.addChatMessage(new ChatMessage("<SquidAPI> No such mod."));
					return;
				}
				else if (v == null) {
					sender.addChatMessage(new ChatMessage("<SquidAPI> ", args.get(1), " is up to date."));
					return;
				}
				sender.addChatMessage(new ChatMessage("<SquidAPI> ", v.getName(), " is currently running version ", v.getOldVersion(), ". The latest available version is ", v.getNewVersion(), ".").setUrl(v.getUpdateUrl()));
			}
		}
	}
}
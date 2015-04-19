/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.command.CommandSquidAPI;
import coolsquid.squidapi.command.ISubCommand;
import coolsquid.squidapi.config.ModConfigHandler;
import coolsquid.squidapi.util.MiscLib;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class UpdateManager {

	public static final UpdateManager INSTANCE = new UpdateManager();

	final Set<VersionContainer> outdatedMods = Sets.newHashSet();

	private UpdateManager() {
		CommandSquidAPI.INSTANCE.registerSubcommand(new SubcommandUpdateChecker());
	}

	public void check(SquidAPIMod mod) {
		if (mod.getCurseUrl() != null && MiscLib.updateChecker()) {
			this.getUpdateThread(mod).start();
		}
	}

	private Thread getUpdateThread(SquidAPIMod mod) {
		return new Thread(new UpdateChecker(mod));
	}

	public void markAsOutdated(SquidAPIMod mod, String newVersion) {
		this.outdatedMods.add(new VersionContainer(mod.getName(), mod.getCurseUrl(), mod.getVersion(), newVersion));
	}

	void disable() {
		MiscLib.SETTINGS.set("updateChecker", false);
		MinecraftForge.EVENT_BUS.unregister(this);
	}

	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public void onGuiInit(InitGuiEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			GuiButton button = new GuiButton(10, 10, 0, "Updates") {
				@Override
				public void mouseReleased(int p_146118_1_, int p_146118_2_) {
					Minecraft.getMinecraft().displayGuiScreen(new GuiUpdates());
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
				UpdateManager.this.disable();
				ModConfigHandler.INSTANCE.setProperty(ModConfigHandler.CATEGORY_GENERAL, "updateChecker", "Enables the update checker.", false);
			}
		}
	}
}
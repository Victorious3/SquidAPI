/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class UpdateManager implements Runnable, UpdaterAPI {

	@Deprecated
	public static final UpdaterAPI INSTANCE = SquidAPI.UPDATER;

	private final Set<UpdateChecker> updateCheckers = Sets.newHashSet();
	private final List<VersionContainer> outdatedMods = Lists.newArrayList();
	private final Thread thread;

	public UpdateManager() {
		this.thread = new Thread(this);
	}

	public void start() {
		boolean enabled = MiscLib.CLIENT && MiscLib.SETTINGS.getBoolean("updateChecker");
		if (enabled) {
			MinecraftForge.EVENT_BUS.register(this);
			this.thread.start();
		}
	}

	@Override
	public void run() {
		for (ModContainer mod: Loader.instance().getActiveModList()) {
			if (mod.getMod() instanceof Updateable) {
				String url = ((Updateable) mod.getMod()).getUrl();
				if (url != null) {
					new UpdateChecker(mod, url, this).check();
				}
			}
		}
		for (UpdateChecker checker: this.updateCheckers) {
			checker.check();
		}
	}

	void markAsOutdated(VersionContainer data) {
		this.outdatedMods.add(data);
		SquidAPI.instance().info(data.getMod().getName(), " is outdated! Version ", data.getLatestVersion(), " is available!");
		SquidAPI.instance().info("The new version may be obtained from: ", data.getFriendlyUrl());
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public void onGuiInit(InitGuiEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			GuiButton button = new GuiButton(10, 10, 0, "Updates") {
				@Override
				public void mouseReleased(int p_146118_1_, int p_146118_2_) {
					GuiScreen screen = new GuiUpdates(UpdateManager.this.outdatedMods);
					Minecraft.getMinecraft().displayGuiScreen(screen);
				}
			};
			button.yPosition = event.gui.height - button.height - 10;
			button.width = 50;
			byte severity = 1;
			for (VersionContainer version: this.outdatedMods) {
				if (version.getSeverity() > severity) {
					severity = version.getSeverity();
				}
			}
			if (severity == 2) {
				button.displayString = "Updates!";
				button.packedFGColour = Color.YELLOW.getRGB();
				button.width += 2;
			}
			else if (severity >= 3) {
				button.displayString = "Updates!!!";
				button.packedFGColour = Color.RED.getRGB();
				button.width += 5;
			}
			event.buttonList.add(button);
		}
	}

	@Override
	public List<VersionContainer> getOutdatedMods() {
		return Lists.newArrayList(this.outdatedMods);
	}

	@Override
	public void registerUpdateChecker(UpdateChecker updateChecker) {
		this.updateCheckers.add(updateChecker);
	}

	@Override
	public void registerUpdateChecker(ModContainer mod, String url) {
		this.registerUpdateChecker(new UpdateChecker(mod, url, this));
	}

	@Override
	public void registerUpdateChecker(String url) {
		this.registerUpdateChecker(Utils.getCurrentMod(), url);
	}
}
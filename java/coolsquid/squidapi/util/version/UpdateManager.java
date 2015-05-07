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
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPI;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class UpdateManager implements Runnable {

	public static final UpdateManager INSTANCE = new UpdateManager();

	private final Set<UpdateChecker> extraCheckers = Sets.newHashSet();
	final List<VersionContainer> outdatedMods = Lists.newArrayList();
	private boolean enabled = true;
	private Thread thread;

	private UpdateManager() {
		if (this.enabled) {
			MinecraftForge.EVENT_BUS.register(this);
			this.thread = new Thread(this);
		}
	}

	public void checkAll() {
		this.thread.start();
	}

	@Override
	public void run() {
		if (this.enabled) {
			for (ModContainer mod: Loader.instance().getActiveModList()) {
				if (mod.getMod() instanceof Updateable) {
					String url = ((Updateable) mod.getMod()).getUrl();
					if (url != null) {
						new UpdateChecker(mod, url).check();
					}
				}
			}
			for (UpdateChecker checker: this.extraCheckers) {
				checker.check();
			}
		}
	}

	void markAsOutdated(VersionContainer data) {
		this.outdatedMods.add(data);
		SquidAPI.instance().info(data.getMod().getName(), " is outdated! Version ", data.getLatestVersion(), " is available!");
		SquidAPI.instance().info("The new version may be obtained from: ", data.getFriendlyUrl());
	}

	void disable() {
		this.enabled = false;
	}

	@SuppressWarnings("unchecked")
	@SubscribeEvent
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
			byte severity = 1;
			for (VersionContainer version: this.outdatedMods) {
				if (version.getSeverity() > severity) {
					severity = version.getSeverity();
				}
			}
			if (severity == 2) {
				button.displayString = "Updates!";
				button.packedFGColour = 16711680;
				button.width += 2;
			}
			else if (severity >= 3) {
				button.displayString = "Updates!!!";
				button.packedFGColour = 16711680;
				button.width += 5;
			}
			event.buttonList.add(button);
		}
	}

	public void registerUpdateChecker(UpdateChecker updateChecker) {
		this.extraCheckers.add(updateChecker);
	}

	public List<VersionContainer> getOutdatedMods() {
		return Lists.newArrayList(this.outdatedMods);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class UpdateManager implements Runnable {

	public static final UpdateManager INSTANCE = new UpdateManager();

	public final Set<UpdateChecker> extraCheckers = Sets.newHashSet();
	final Set<VersionContainer> outdatedMods = Sets.newHashSet();
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
			/*for (ModContainer mod: Loader.instance().getActiveModList()) {
				if (mod.getMod() instanceof Updateable) {
					this.getUpdateChecker(mod).check();
				}
			}*/
			for (UpdateChecker checker: this.extraCheckers) {
				checker.check();
			}
		}
	}

	/*private UpdateChecker getUpdateChecker(ModContainer mod) {
		return new UpdateChecker(mod, ((Updateable) mod.getMod()).getUrl());
	}*/

	void markAsOutdated(VersionContainer data) {
		this.outdatedMods.add(data);
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
}
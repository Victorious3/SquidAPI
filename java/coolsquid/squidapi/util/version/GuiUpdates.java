/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import coolsquid.squidapi.util.io.WebUtils;

public class GuiUpdates extends GuiScreen {

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		int a = 0;
		for (VersionContainer mod: UpdateManager.INSTANCE.outdatedMods) {
			GuiButton b = new GuiButtonMod(1, 0, this.height / 2 - 30 + a, mod.getName() + " " + mod.getNewVersion(), mod);
			b.xPosition = this.width / 2 - (b.width / 2);
			this.buttonList.add(b);
			a += b.height + 4;
		}
		GuiButton button = new GuiButton(0, 10, 0, "Return") {
			@Override
			public void mouseReleased(int p_146118_1_, int p_146118_2_) {
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
		};
		button.yPosition = this.height - button.height - 10;
		button.width = 50;
		this.buttonList.add(button);
	}

	@Override
	public void drawScreen(int mouseRelX, int mouseRelY, float tickTime) {
		this.drawDefaultBackground();
		super.drawScreen(mouseRelX, mouseRelY, tickTime);
		String line1 = "Available updates:";
		this.drawCenteredString(this.fontRendererObj, line1, this.width / 2, this.height / 2 - 60, 16777215);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 1) {
			WebUtils.openBrowser(((GuiButtonMod) button).getMod().getUpdateUrl());
		}
	}

	private static class GuiButtonMod extends GuiButton {

		private final VersionContainer mod;

		public GuiButtonMod(int id, int x, int y, String name, VersionContainer mod) {
			super(id, x, y, name);
			this.mod = mod;
		}

		public VersionContainer getMod() {
			return this.mod;
		}
	}
}
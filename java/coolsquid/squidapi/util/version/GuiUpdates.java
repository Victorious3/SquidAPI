/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import coolsquid.squidapi.client.gui.GuiBase;
import coolsquid.squidapi.util.io.WebUtils;

public class GuiUpdates extends GuiBase {

	private int showMessage;
	private final File modFolder = new File("./mods");

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		int a = 0;
		for (VersionContainer version: UpdateManager.INSTANCE.outdatedMods) {
			GuiButton b = new GuiButtonMod(1, 0, this.height / 2 - 20 + a, version.getMod().getName() + " " + version, version.getFriendlyUrl());
			b.xPosition = this.width / 2 - (b.width / 2);
			if (version.getSeverity() >= 2) {
				b.packedFGColour = 16711680;
			}
			this.buttonList.add(b);
			a += b.height + 4;
		}
		GuiButton button = new GuiButton(0, 10, 0, "Return");
		button.yPosition = this.height - button.height - 10;
		button.width = 50;
		this.buttonList.add(button);
		if (Desktop.isDesktopSupported()) {
			GuiButton button2 = new GuiButton(2, this.width - 60, 0, "Mods");
			button2.yPosition = this.height - button.height - 10;
			button2.width = 50;
			this.buttonList.add(button2);
		}
	}

	@Override
	public void drawScreen(int mouseRelX, int mouseRelY, float tickTime) {
		this.drawDefaultBackground();
		super.drawScreen(mouseRelX, mouseRelY, tickTime);
		if (UpdateManager.INSTANCE.outdatedMods.isEmpty()) {
			this.drawString("No updates available.", this.width / 2, this.height / 2);
		}
		else {
			String line1 = "Available updates:";
			this.drawCenteredString(this.fontRendererObj, line1, this.width / 2, this.height / 2 - 60, 16777215);
		}
		if (!Desktop.isDesktopSupported()) {
			this.drawString("Your desktop is not supported. Clicking the buttons will not open the site,", this.width / 2, 20);
			this.drawString("but rather copy the url to your clipboard.", this.width / 2, 30);
		}
		if (this.showMessage > 0) {
			this.drawString("The url was copied to your clipboard.", this.width / 2, this.height - 20);
			this.showMessage--;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 2) {
			try {
				Desktop.getDesktop().open(this.modFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (button.id == 1) {
			if (Desktop.isDesktopSupported()) {
				WebUtils.openBrowser(((GuiButtonMod) button).getUrl());
			}
			else {
				GuiScreen.setClipboardString(((GuiButtonMod) button).getUrl());
				this.showMessage = 50;
			}
		}
		else if (button.id == 0) {
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	private static class GuiButtonMod extends GuiButton {

		private final String url;

		public GuiButtonMod(int id, int x, int y, String name, String url) {
			super(id, x, y, name);
			this.url = url;
		}

		public String getUrl() {
			return this.url;
		}
	}
}
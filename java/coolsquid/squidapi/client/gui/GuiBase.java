/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBase extends GuiScreen {

	@Override
	public void drawScreen(int mouseRelX, int mouseRelY, float tickTime) {
		this.drawDefaultBackground();
		super.drawScreen(mouseRelX, mouseRelY, tickTime);
	}

	public void drawString(String string, int x, int y) {	
		this.drawCenteredString(this.fontRendererObj, string, x, y, 16777215);
	}

	@SuppressWarnings("unchecked")
	public void addButton(GuiButton button) {
		this.buttonList.add(button);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import cpw.mods.fml.client.CustomModLoadingErrorDisplayException;

public class LoadingException extends CustomModLoadingErrorDisplayException {

	private static final long serialVersionUID = -5764070459283605920L;

	private final String text;

	public LoadingException(String text) {
		this.text = text;
	}

	@Override
	public void initGui(GuiErrorScreen screen, FontRenderer font) {

	}

	@Override
	public void drawScreen(GuiErrorScreen screen, FontRenderer font, int mouseRelX, int mouseRelY, float tickTime) {
		screen.drawString(font, this.text, screen.width / 2 - (font.getStringWidth(this.text) / 2), screen.height / 2 - (font.FONT_HEIGHT / 2), 16777215);
	}
}
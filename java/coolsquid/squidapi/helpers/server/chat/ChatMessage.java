/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers.server.chat;

import java.net.URL;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import coolsquid.squidapi.util.Utils;

public class ChatMessage extends ChatComponentStyle {

	private String text;

	public ChatMessage(String... msg) {
		this.text = Utils.newString2(msg);
	}
	
	public ChatMessage setColor(EnumChatFormatting color) {
		this.getChatStyle().setColor(color);
		return this;
	}
	
	public ChatMessage setColor(Color color) {
		this.getChatStyle().setColor(color.getColor());
		return this;
	}
	
	public ChatMessage setBold() {
		this.getChatStyle().setBold(true);
		return this;
	}

	public ChatMessage setItalic() {
		this.getChatStyle().setItalic(true);
		return this;
	}

	public ChatMessage setUnderlined() {
		this.getChatStyle().setUnderlined(true);
		return this;
	}
	
	public ChatMessage setUrl(String url) {
		this.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, url));
		return this;
	}

	public ChatMessage setUrl(URL url) {
		this.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, url.toString()));
		return this;
	}

	public ChatMessage setText(String text) {
		this.text = text;
		return this;
	}

	public ChatMessage append(String msg) {
		ChatMessage a = new ChatMessage(msg);
		this.appendSibling(a);
		return a;
	}
	
	public static enum Color {
		BLACK(EnumChatFormatting.BLACK),
	    DARK_BLUE(EnumChatFormatting.DARK_BLUE),
	    DARK_GREEN(EnumChatFormatting.DARK_GREEN),
	    DARK_AQUA(EnumChatFormatting.DARK_AQUA),
	    DARK_RED(EnumChatFormatting.DARK_RED),
	    DARK_PURPLE(EnumChatFormatting.DARK_PURPLE),
	    GOLD(EnumChatFormatting.GOLD),
	    GRAY(EnumChatFormatting.GRAY),
	    DARK_GRAY(EnumChatFormatting.DARK_GRAY),
	    BLUE(EnumChatFormatting.BLUE),
	    GREEN(EnumChatFormatting.GREEN),
	    AQUA(EnumChatFormatting.AQUA),
	    RED(EnumChatFormatting.RED),
	    LIGHT_PURPLE(EnumChatFormatting.LIGHT_PURPLE),
	    YELLOW(EnumChatFormatting.YELLOW),
	    WHITE(EnumChatFormatting.WHITE);
		
		private final EnumChatFormatting color;

		private Color(EnumChatFormatting color) {
			this.color = color;
		}

		public EnumChatFormatting getColor() {
			return this.color;
		}
	}

	@Override
	public String getUnformattedTextForChat() {
		return this.text;
	}

	@Override
	public IChatComponent createCopy() {
		return new ChatMessage(this.text).setChatStyle(this.getChatStyle());
	}
}
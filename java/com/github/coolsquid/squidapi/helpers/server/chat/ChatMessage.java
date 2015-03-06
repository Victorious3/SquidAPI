/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers.server.chat;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatMessage extends ChatComponentText {

	public ChatMessage(String msg) {
		super(msg);
		this.getChatStyle().setColor(EnumChatFormatting.BLUE);
	}
	
	public ChatMessage(ChatMessage... components) {
		super("");
		for (ChatMessage component: components) {
			this.appendSibling(component);
		}
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
	
	public ChatMessage setUnderlined() {
		this.getChatStyle().setUnderlined(true);
		return this;
	}
	
	public ChatMessage setUrl(String url) {
		this.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, url));
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
}
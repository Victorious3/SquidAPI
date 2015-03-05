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
	
	public ChatMessage setColor(EnumChatFormatting color) {
		this.getChatStyle().setColor(color);
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
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.chat;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatMessage extends ChatComponentText {

	public ChatMessage(String text) {
		super(text);
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
}
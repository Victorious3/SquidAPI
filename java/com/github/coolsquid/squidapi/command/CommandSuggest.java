/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;

import com.github.coolsquid.squidapi.SquidAPIMod;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.util.Suggestion;

public class CommandSuggest extends CommandBase {

	public CommandSuggest() {
		super("suggest", "");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		Suggestion a = SquidAPIMod.getRandomSuggestedMod();
		ChatMessage b = new ChatMessage("<SquidAPI> ");
		b.appendSibling(new ChatMessage("Check out ", a.getSuggestion(), ". ", a.getReason()).setUrl(a.getUrl()));
		sender.addChatMessage(b);
	}
}
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

	private Suggestion suggestion;

	public CommandSuggest() {
		super("suggest", "");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		this.suggestion = SquidAPIMod.getRandomSuggestedModEnsureNotSame(this.suggestion);
		ChatMessage b = new ChatMessage("<SquidAPI> ");
		b.appendSibling(new ChatMessage("Check out ", this.suggestion.getSuggestion(), ". ", this.suggestion.getReason()).setUrl(this.suggestion.getUrl()));
		sender.addChatMessage(b);
	}
}
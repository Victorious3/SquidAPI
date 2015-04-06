/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.Suggestion;
import coolsquid.squidapi.util.SuggestionManager;

public class CommandSuggest extends CommandBase {

	private Suggestion suggestion;

	public CommandSuggest() {
		super("suggest", "");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		this.suggestion = SuggestionManager.INSTANCE.getRandomSuggestedModEnsureNotSame(this.suggestion);
		ChatMessage b = new ChatMessage("<SquidAPI> ");
		b.appendSibling(new ChatMessage("Check out ", this.suggestion.getName(), ". ", this.suggestion.getDescription()).setUrl(this.suggestion.getUrl()));
		sender.addChatMessage(b);
	}
}
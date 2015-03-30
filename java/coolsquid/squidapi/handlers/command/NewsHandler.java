/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers.command;

import net.minecraft.command.ICommandSender;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.formatting.SWTFParser;

public class NewsHandler extends Thread {
	
	private ICommandSender sender;
	
	public NewsHandler(ICommandSender sender) {
		this.sender = sender;
	}

	@Override
	public void run() {
		for (ChatMessage msg: new SWTFParser("http://pastebin.com/raw.php?i=z20CbwVE").get()) {
			this.sender.addChatMessage(msg);
		}
	}
}
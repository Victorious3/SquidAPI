/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers.command;

import java.net.URL;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import com.github.coolsquid.squidapi.util.io.WebUtils;

public class NewsHandler extends Thread {
	
	private ICommandSender sender;
	
	public NewsHandler(ICommandSender sender) {
		this.sender = sender;
	}

	@Override
	public void run() {
		URL url = WebUtils.newURL("http://pastebin.com/raw.php?i=z20CbwVE");
		this.sender.addChatMessage(new ChatComponentText("§4<" + WebUtils.getLine(url, 0) + ">§r " + WebUtils.getLine(url, 1)));
	}
}
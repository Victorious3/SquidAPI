/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandNews extends CommandBase {

	@Override
	public String getCommandName() {
		return "news";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		NewsHandler.sender = sender;
		NewsHandler thread = new NewsHandler();
		thread.start();
	}
}
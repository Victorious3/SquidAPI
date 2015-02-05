/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;

public class CommandNews extends CommandBase {

	public CommandNews() {
		super("news", "Gives you the latest news from CoolSquid!");
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		NewsHandler.sender = sender;
		NewsHandler thread = new NewsHandler();
		thread.start();
	}
}
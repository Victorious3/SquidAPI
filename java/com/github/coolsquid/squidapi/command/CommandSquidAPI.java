/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import com.github.coolsquid.squidapi.handlers.DevEnvironmentEventHandler;
import com.github.coolsquid.squidapi.handlers.command.NewsHandler;

public class CommandSquidAPI extends CommandBase {

	public CommandSquidAPI() {
		super("SquidAPI", "", false);
	}
	
	private void sendMsg(ICommandSender sender, String msg) {
		sender.addChatMessage(new ChatComponentText("§4<SquidAPI>§r " + msg));
	}
	
	private void sendHelp(ICommandSender sender, String msg) {
		this.sendMsg(sender, "/" + this.getCommandName() + " " + msg);
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		String subcommand = args[0];
		if (subcommand.equals("news")) {
			NewsHandler thread = new NewsHandler(sender);
			thread.start();
		}
		else if (subcommand.equals("help")) {
			this.sendMsg(sender, "Available commands:");
			this.sendHelp(sender, "help");
			this.sendHelp(sender, "news");
		}
		else if (subcommand.equals("togglesuperspeed")) {
			DevEnvironmentEventHandler.speedy = !DevEnvironmentEventHandler.speedy;
		}
		else {
			this.sendMsg(sender, "Type \"/" + this.getCommandName() + " help\" to recieve a list of subcommands.");
		}
	}
}
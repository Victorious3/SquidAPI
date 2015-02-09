/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class CommandBase implements ICommand {
	
	private String name;
	private String desc;
	private String[] aliases;

	public CommandBase(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public CommandBase(String name, String desc, String[] aliases) {
		this.name = name;
		this.desc = desc;
		this.aliases = aliases;
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return desc;
	}

	@Override
	public List<?> getCommandAliases() {
		if (aliases == null) {
			return null;
		}
		ArrayList<String> l = new ArrayList<String>();
		for (int a = 0; a < aliases.length; a++) {
			l.add(aliases[a]);
		}
		return l;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}
}
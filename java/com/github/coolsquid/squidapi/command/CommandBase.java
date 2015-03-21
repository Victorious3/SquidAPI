/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.List;
import java.util.Map;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import com.github.coolsquid.squidapi.helpers.server.OpHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CommandBase implements ICommand {
	
	private String name;
	private String desc;
	private boolean needsop;
	private Map<String, ISubCommand> subcommands = Maps.newHashMap();
	private List<String> autofilloptions;

	public CommandBase(String name, String desc, boolean needsop) {
		this.name = name;
		this.desc = desc;
		this.needsop = needsop;
	}

	public CommandBase(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public CommandBase(String name, String desc, boolean needsop, ISubCommand... subcommands) {
		this.name = name;
		this.desc = desc;
		this.needsop = needsop;
		for (ISubCommand subcommand: subcommands) {
			this.subcommands.put(subcommand.getName(), subcommand);
			this.autofilloptions.add(subcommand.getName());
		}
	}

	public CommandBase(String name, String desc, ISubCommand... subcommands) {
		this.name = name;
		this.desc = desc;
		for (ISubCommand subcommand: subcommands) {
			this.subcommands.put(subcommand.getName(), subcommand);
		}
	}

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return this.name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return this.desc;
	}

	@Override
	public List<?> getCommandAliases() {
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (this.needsop) {
			return OpHelper.isOp(sender.getCommandSenderName());
		}
		else {
			return true;
		}
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args) {
		return Lists.newArrayList(this.subcommands.keySet());
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length == 0) {
			return;
		}
		if (this.subcommands.containsKey(args[0])) {
			List<String> args2 = Lists.newArrayList();
			for (int a = 1; a < args.length; a++) {
				args2.add(args[a]);
			}
			this.subcommands.get(args[0]).execute(sender, args2);
		}
	}
}
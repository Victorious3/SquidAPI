/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import com.github.coolsquid.squidapi.helpers.server.OpHelper;

public class CommandBase implements ICommand {
	
	private String name;
	private String desc;
	private boolean needsop;

	public CommandBase(String name, String desc, boolean needsop) {
		this.name = name;
		this.desc = desc;
		this.needsop = needsop;
	}

	public CommandBase(String name, String desc) {
		this.name = name;
		this.desc = desc;
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
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {}
}
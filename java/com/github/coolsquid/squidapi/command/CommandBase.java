/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.ArrayList;
import java.util.Arrays;
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
		return false;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(aliases);
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CommandBase)) {
			return false;
		}
		CommandBase other = (CommandBase) obj;
		if (!Arrays.equals(aliases, other.aliases)) {
			return false;
		}
		if (desc == null) {
			if (other.desc != null) {
				return false;
			}
		} else if (!desc.equals(other.desc)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CommandBase [name=" + name + ", desc=" + desc + ", aliases=" + Arrays.toString(aliases) + "]";
	}
}
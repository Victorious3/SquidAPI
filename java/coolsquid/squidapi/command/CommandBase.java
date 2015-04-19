/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import java.util.List;
import java.util.Map;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import coolsquid.squidapi.helpers.server.ServerHelper;

public class CommandBase implements ICommand {
	
	private String name;
	private String desc;
	private boolean needsop;
	private Map<String, ISubCommand> subcommands = Maps.newHashMap();
	private List<String> autofilloptions;

	public CommandBase(String name, String desc, boolean needsop) {
		this(name, desc);
		this.needsop = needsop;
	}

	public CommandBase(String name, String desc) {
		this.name = name;
		this.desc = desc;
		this.needsop = this instanceof OpOnly;
	}
	
	public CommandBase(String name, String desc, boolean needsop, ISubCommand... subcommands) {
		this(name, desc, subcommands);
		this.needsop = needsop;
	}

	public CommandBase(String name, String desc, ISubCommand... subcommands) {
		this(name, desc);
		this.autofilloptions = Lists.newArrayList();
		for (ISubCommand subcommand: subcommands) {
			this.subcommands.put(subcommand.getName(), subcommand);
			this.autofilloptions.add(subcommand.getName());
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
			return sender.canCommandSenderUseCommand(ServerHelper.getServerInstance().getOpPermissionLevel(), this.name);
		}
		return true;
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

	public void registerSubcommand(ISubCommand subcommand) {
		this.subcommands.put(subcommand.getName(), subcommand);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public interface ISubCommand {
	public abstract String getName();
	public abstract void execute(ICommandSender sender, List<String> args);
}
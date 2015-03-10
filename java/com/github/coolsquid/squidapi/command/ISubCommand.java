/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public interface ISubCommand {
	String getName();
	void execute(ICommandSender sender, List<String> args);
}
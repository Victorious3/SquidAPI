/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import java.util.Map;
import java.util.Set;

import net.minecraft.command.ICommandSender;

import com.github.coolsquid.squidapi.Disableable;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage.Color;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class CommandDisable extends CommandBase {

	public static final Map<String, Disableable> disableables = Maps.newHashMap();
	static final Set<Disableable> disabledmods = Sets.newHashSet();
	
	public CommandDisable() {
		super("disable", "", true);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		Disableable mod = disableables.get(args[0]);
		if (mod == null) {
			sender.addChatMessage(new ChatMessage("<SquidAPI> No such mod.").setColor(Color.RED));
			return;
		}
		if (disabledmods.contains(mod)) {
			sender.addChatMessage(new ChatMessage("<SquidAPI> The mod is already disabled.").setColor(Color.RED));
			return;
		}
		if (mod.disable()) {
			disabledmods.add(mod);
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Disabled ", args[0], ".")));
		}
		else {
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Could not disable ", args[0], ".")).setColor(Color.RED));
		}
	}
}
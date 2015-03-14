/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;

import com.github.coolsquid.squidapi.Disableable;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage.Color;
import com.github.coolsquid.squidapi.util.Utils;

public class CommandEnable extends CommandBase {
	
	public CommandEnable() {
		super("enable", "", true);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		Disableable mod = CommandDisable.disableables.get(args[0]);
		if (mod == null) {
			sender.addChatMessage(new ChatMessage("<SquidAPI> No such mod.").setColor(Color.RED));
			return;
		}
		if (!CommandDisable.disabledmods.contains(mod)) {
			sender.addChatMessage(new ChatMessage("<SquidAPI> The mod is already enabled.").setColor(Color.RED));
			return;
		}
		try {
			mod.enable();
			CommandDisable.disabledmods.remove(mod);
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Enabled ", args[0], ".")));
		} catch (Exception e) {
			e.printStackTrace();
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Could not disable ", args[0], ".")).setColor(Color.RED));
		}
	}
}
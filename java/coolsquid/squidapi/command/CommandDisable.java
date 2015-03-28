/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import java.util.Map;
import java.util.Set;

import net.minecraft.command.ICommandSender;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import coolsquid.squidapi.Disableable;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.helpers.server.chat.ChatMessage.Color;
import coolsquid.squidapi.util.Utils;

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
		try {
			mod.disable();
			disabledmods.add(mod);
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Disabled ", args[0], ".")));
		} catch (Exception e) {
			e.printStackTrace();
			sender.addChatMessage(new ChatMessage(Utils.newString("<SquidAPI> Could not disable ", args[0], ".")).setColor(Color.RED));
		}
	}
}
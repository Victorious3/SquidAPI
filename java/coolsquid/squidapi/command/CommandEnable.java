/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import coolsquid.squidapi.Disableable;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.helpers.server.chat.ChatMessage.Color;
import coolsquid.squidapi.util.StringUtils;

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
			sender.addChatMessage(new ChatMessage(StringUtils.newString("<SquidAPI> Enabled ", args[0], ".")));
		} catch (Exception e) {
			e.printStackTrace();
			sender.addChatMessage(new ChatMessage(StringUtils.newString("<SquidAPI> Could not disable ", args[0], ".")).setColor(Color.RED));
		}
	}
}
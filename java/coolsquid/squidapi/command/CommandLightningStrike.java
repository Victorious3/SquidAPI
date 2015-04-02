/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;

public class CommandLightningStrike extends CommandBase {

	public CommandLightningStrike() {
		super("strikewithlightning", "");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender instanceof EntityPlayer) {
			try {
				EntityPlayer player = (EntityPlayer) sender;
				player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, player.posX, player.posY - 1, player.posZ));
			} catch (IndexOutOfBoundsException e) {
				sender.addChatMessage(new ChatMessage("Not enough arguments!"));
			}
		}
		else {
			sender.addChatMessage(new ChatMessage("Only players can use this command!"));
		}
	}
}
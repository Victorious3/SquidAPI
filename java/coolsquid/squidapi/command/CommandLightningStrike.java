/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.Utils;
import coolsquid.squidapi.util.math.DoubleUtils;

public class CommandLightningStrike extends CommandBase {

	public CommandLightningStrike() {
		super("strikewithlightning", "");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender instanceof EntityPlayer) {
			try {
				EntityPlayer player = (EntityPlayer) sender;
				if (!player.worldObj.isRaining()) {
					sender.addChatMessage(new ChatMessage("You must wait for the world to start raining!"));
					return;
				}
				double x = player.posX;
				double y = player.posY;
				double z = player.posZ;
				if (args.length == 3) {
					x += DoubleUtils.parseDouble(args[0]);
					y += DoubleUtils.parseDouble(args[1]);
					z += DoubleUtils.parseDouble(args[2]);
				}
				EntityLightningBolt a = new EntityLightningBolt(player.worldObj, x, y, z);
				a.boltLivingTime = Utils.getRandInt(10, 20);
				player.worldObj.addWeatherEffect(a);
			} catch (IndexOutOfBoundsException e) {
				sender.addChatMessage(new ChatMessage("Not enough arguments!"));
			}
		}
		else {
			sender.addChatMessage(new ChatMessage("Only players can use this command!"));
		}
	}
}
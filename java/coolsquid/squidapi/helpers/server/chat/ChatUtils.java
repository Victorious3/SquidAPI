/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers.server.chat;

import net.minecraft.command.ICommandSender;
import coolsquid.squidapi.helpers.server.ServerHelper;

public class ChatUtils {
	
	public static void sendPrivateMsg(String player, String msg) {
		ServerHelper.getPlayerFromName(player).addChatMessage(new ChatMessage(msg));
	}
	
	public static void sendPrivateMsg(ICommandSender player, String... msg) {
		player.addChatMessage(new ChatMessage(msg));
	}
	
	public static void sendPublicMsg(String msg) {
		ServerHelper.sendMsg(msg);
	}
	
	public static void sendPrivateMsg(String player, ChatMessage msg) {
		ServerHelper.getPlayerFromName(player).addChatMessage(msg);
	}
	
	public static void sendPublicMsg(ChatMessage msg) {
		ServerHelper.sendMsg(msg);
	}
}
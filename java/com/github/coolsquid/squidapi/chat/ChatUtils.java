package com.github.coolsquid.squidapi.chat;

import com.github.coolsquid.squidapi.helpers.server.ServerHelper;

public class ChatUtils {
	
	public static void sendPrivateMsg(String player, String msg) {
		ServerHelper.getPlayerFromName(player).addChatMessage(new ChatMessage(msg));
	}
	
	public static void sendPublicMsg(String msg) {
		ServerHelper.sendMsg(msg);
	}
}
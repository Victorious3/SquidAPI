/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers.server;

import java.util.ArrayList;
import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;

import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;

public class ServerHelper {
	
	/**
	 * @return - A list of whitelisted players.
	 */
	
	public static String[] getWhitelist() {
		return MinecraftServer.getServer().getConfigurationManager().func_152599_k().func_152685_a();
	}
	
	/**
	 * Kicks a player.
	 * @param player - The player to kick.
	 * @param reason - The reason for the kick.
	 */
	
	public static void kick(EntityPlayer player, String reason) {
		((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer(reason);
	}
	
	/**
	 * Kicks and bans a player.
	 * @param player - The player to kickban.
	 * @param reason - The reason for the kickban.
	 */
	
	public static void ban(EntityPlayer player, String reason) {
		BanHelper.ban(new UserListBansEntry(player.getGameProfile(), Calendar.getInstance().getTime(), "SafeChat", null, reason));
		kick(player, reason);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<EntityPlayerMP> getAllPlayers() {
		return (ArrayList<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
	}
	
	public static ArrayList<String> getAllDisplayNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (EntityPlayerMP a: getAllPlayers()) {
			names.add(a.getDisplayName());
		}
		return names;
	}
	
	/**
	 * Gets the EntityPlayerMP from a username.
	 * @param name - The username of the player.
	 * @return EntityPlayerMP
	 */
	
	public static EntityPlayerMP getPlayerFromName(String name) {
		return MinecraftServer.getServer().getConfigurationManager().func_152612_a(name);
	}
	
	public static EntityPlayerMP getPlayerFromNick(String name) {
		for (Object a: MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
			EntityPlayerMP player = (EntityPlayerMP) a;
			if (player.getDisplayName().equals(name)) {
				return player;
			}
		}
		return null;
	}
	
	/**
	 * Sets the max view distance.
	 * @param distance - The new max view distance.
	 */
	
	public static void setViewDistance(int distance) {
		MinecraftServer.getServer().getConfigurationManager().func_152611_a(distance);
	}
	
	/**
	 * Kicks all the players.
	 */
	
	public static void kickAll() {
		MinecraftServer.getServer().getConfigurationManager().removeAllPlayers();
	}
	
	/**
	 * Respawns a player.
	 * @param player - The player to respawn.
	 * @param dimension - The dimension to respawn the player in.
	 */
	
	public static void respawnPlayer(EntityPlayer player, int dimension) {
		MinecraftServer.getServer().getConfigurationManager().respawnPlayer((EntityPlayerMP) player, dimension, true);
	}
	
	/**
	 * Sends a public chat message.
	 * @param msg - The message.
	 */
	
	public static void sendMsg(String msg) {
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatMessage(msg));
	}
	
	public static void sendMsg(ChatMessage msg) {
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(msg);
	}
}
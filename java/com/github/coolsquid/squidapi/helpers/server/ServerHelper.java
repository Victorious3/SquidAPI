/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers.server;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;

public class ServerHelper {
	
	private static MinecraftServer getServer() {
		MinecraftServer server = MinecraftServer.getServer();
		if (server == null) {
			StackTraceElement s = new Throwable().getStackTrace()[2];
			String clazz = s.getClassName();
			String method = s.getMethodName();
			int line = s.getLineNumber();
			LoadController loader = ReflectionHelper.in(Loader.class).field("modController", "modController").get(Loader.instance());
			LoaderState state = ReflectionHelper.in(LoadController.class).field("state", "state").get(loader);
			if (state != LoaderState.SERVER_STARTED) {
				LogHelper.bigWarning(Level.FATAL, Utils.newString("A mod tried to access the MinecraftServer instance during ", state.toString().toLowerCase(), "!"));
			}
			LogHelper.fatal("The error was caused by: ", clazz, ":", method, ":", line, ".");
			throw new NullPointerException("No existing MinecraftServer instance.");
		}
		return server;
	}
	
	/**
	 * @return - A list of whitelisted players.
	 */
	
	public static String[] getWhitelist() {
		return getServer().getConfigurationManager().func_152599_k().func_152685_a();
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
		return (ArrayList<EntityPlayerMP>) getServer().getConfigurationManager().playerEntityList;
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
		return getServer().getConfigurationManager().func_152612_a(name);
	}
	
	public static EntityPlayerMP getPlayerFromNick(String name) {
		for (Object a: getServer().getConfigurationManager().playerEntityList) {
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
		getServer().getConfigurationManager().func_152611_a(distance);
	}
	
	/**
	 * Kicks all the players.
	 */
	
	public static void kickAll() {
		getServer().getConfigurationManager().removeAllPlayers();
	}
	
	/**
	 * Respawns a player.
	 * @param player - The player to respawn.
	 * @param dimension - The dimension to respawn the player in.
	 */
	
	public static void respawnPlayer(EntityPlayer player, int dimension) {
		getServer().getConfigurationManager().respawnPlayer((EntityPlayerMP) player, dimension, true);
	}
	
	/**
	 * Sends a public chat message.
	 * @param msg - The message.
	 */
	
	public static void sendMsg(String msg) {
		getServer().getConfigurationManager().sendChatMsg(new ChatMessage(msg));
	}
	
	public static void sendMsg(ChatMessage msg) {
		getServer().getConfigurationManager().sendChatMsg(msg);
	}
	
	public static void removeCommand(String command) {
		getServer().getCommandManager().getCommands().remove("disable");
	}
}
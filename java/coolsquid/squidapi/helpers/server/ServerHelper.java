/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers.server;

import io.netty.channel.Channel;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;

import com.google.common.collect.Lists;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.reflection.ReflectionHelper;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.StringUtils;
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
			LoadController loader = ReflectionHelper.in(Loader.instance()).field("modController", "modController").get();
			LoaderState state = ReflectionHelper.in(loader).field("state", "state").get();
			SquidAPI.instance().fatal(StringUtils.newString("LoaderState: ", state.toString()));
			SquidAPI.instance().fatal("The error occured in: ", clazz, ".", method, ":", line, ".");
			throw new NullPointerException("No existing MinecraftServer instance.");
		}
		return server;
	}

	public static MinecraftServer getServerInstance() {
		return getServer();
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
	public static List<EntityPlayerMP> getAllPlayers() {
		return getServer().getConfigurationManager().playerEntityList;
	}

	public static List<String> getAllDisplayNames() {
		List<String> names = Lists.newArrayList();
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

	public static void removeCommand(String name) {
		if (!MiscLib.getBlacklist().contains(name)) {
			getCommands().remove(name);
		}
	}

	public static void registerCommand(ICommand command) {
		getCommands().put(command.getCommandName(), command);
	}

	public static void clearCommands() {
		getCommands().clear();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, ICommand> getCommands() {
		return getServer().getCommandManager().getCommands();
	}

	public static void disconnectPlayer(EntityPlayerMP player) {
		player.playerNetServerHandler.netManager.channel().close();
	}

	public static Channel getChannel(EntityPlayerMP player) {
		return player.playerNetServerHandler.netManager.channel();
	}

	public static void sendPacket(EntityPlayerMP player, Packet packet) {
		player.playerNetServerHandler.sendPacket(packet);
	}
}
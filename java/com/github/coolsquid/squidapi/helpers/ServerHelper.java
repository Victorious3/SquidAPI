package com.github.coolsquid.squidapi.helpers;

import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.util.ChatComponentText;

import com.mojang.authlib.GameProfile;

public class ServerHelper {
	
	/**
	 * @return - A list of ops.
	 */
	
	public static String[] getOps() {
		return MinecraftServer.getServer().getConfigurationManager().func_152603_m().func_152685_a();
	}
	
	/**
	 * @return - A list of banned players.
	 */
	
	public static String[] getBans() {
		return MinecraftServer.getServer().getConfigurationManager().func_152608_h().func_152685_a();
	}
	
	/**
	 * @return - A list of whitelisted players.
	 */
	
	public static String[] getWhitelist() {
		return MinecraftServer.getServer().getConfigurationManager().func_152599_k().func_152685_a();
	}
	
	/**
	 * Bans a player.
	 * @param player - The GameProfile of the player to ban.
	 * @param reason - The reason for the ban.
	 */
	
	public static void ban(GameProfile player, String reason) {
		MinecraftServer.getServer().getConfigurationManager().func_152608_h().func_152687_a(new UserListBansEntry(player, Calendar.getInstance().getTime(), "SafeChat", null, reason));
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
	
	public static void kickBan(EntityPlayer player, String reason) {
		MinecraftServer.getServer().getConfigurationManager().func_152608_h().func_152687_a(new UserListBansEntry(player.getGameProfile(), Calendar.getInstance().getTime(), "SafeChat", null, reason));
		kick(player, reason);
	}
	
	/**
	 * Gets the EntityPlayerMP from a username.
	 * @param name - The username of the player.
	 * @return EntityPlayerMP
	 */
	
	public static EntityPlayerMP getPlayerFromName(String name) {
		return MinecraftServer.getServer().getConfigurationManager().func_152612_a(name);
	}
	
	/**
	 * Checks whether a player is op.
	 * @param player - The player to check for op.
	 * @return - True if the player is op.
	 */
	
	public static boolean isOp(String player) {
		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(getPlayerFromName(player).getGameProfile());
	}
	
	/**
	 * Deops a player.
	 * @param player - The player to deop.
	 */
	
	public static void deop(GameProfile player) {
		MinecraftServer.getServer().getConfigurationManager().func_152610_b(player);
	}
	
	/**
	 * Ops a player.
	 * @param player - The player to make op.
	 */
	
	public static void op(GameProfile player) {
		MinecraftServer.getServer().getConfigurationManager().func_152605_a(player);
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
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(msg));
	}
}
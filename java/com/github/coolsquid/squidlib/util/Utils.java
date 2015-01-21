package com.github.coolsquid.squidlib.util;

import java.util.Random;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.launchwrapper.Launch;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class Utils {
	
	/**
	 * Sends a chat message to a player.
	 * @param player
	 * @param msg
	 */
	
	public static final void sendMsg(EntityClientPlayerMP player, String msg) {
		player.sendChatMessage(msg);
	}
	
	private static Random r = new Random();
	
	/**
	 * Has a d/k chance to return true.
	 * @param d
	 * @param k
	 * @return boolean
	 */
	
	public static boolean getChance(int d, int k) {
		int a = r.nextInt(k) + 1;
		return a <= d;
	}
	
	/**
	 * Checks if Minecraft is running in a deobfuscated enviroment.
	 */
	
	public static final boolean developmentEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	
	/**
	 * Checks if the server is running Bukkit.
	 * @return boolean
	 */
	
	public static final boolean isBukkit() {
		try {
			return Class.forName("org.bukkit.Bukkit") != null;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * Checks if the mod is running on the correct MC version.
	 * @return boolean
	 */
	
	public static final boolean wrongVersion() {
		return !Loader.MC_VERSION.equals(Data.mcversion);
	}
	
	/**
	 * Checks if the mod is running on a client.
	 * @return boolean
	 */
	
	public static final boolean isClient() {
		return FMLCommonHandler.instance().getSide().equals(Side.CLIENT);
	}
	
	/**
	 * Checks if Minecraft is using Java 8.
	 * @return boolean
	 */
	
	public static final boolean isJava8() {
		return System.getProperty("java.version").contains("1.8.0_");
	}
}
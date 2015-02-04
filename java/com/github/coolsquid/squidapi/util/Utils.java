/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.Random;

import net.minecraft.launchwrapper.Launch;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class Utils {
	
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
	
	public static int getRandInt(int min, int max) {
		return min + r.nextInt(max - min + 1);
	}
	
	/**
	 * Checks if the server is running Bukkit.
	 * @return boolean
	 */
	
	public static boolean isBukkit() {
		try {
			return Class.forName("org.bukkit.Bukkit") != null;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * Checks if the mod is running on a client.
	 * @return boolean
	 */
	
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().equals(Side.CLIENT);
	}
	
	/**
	 * Checks if Minecraft is using Java 8.
	 * @return boolean
	 */
	
	public static boolean isJava8() {
		return System.getProperty("java.version").contains("1.8.0_");
	}
	
	/**
	 * Checks if the mod is running on the correct MC version.
	 * @return boolean
	 */
	
	public static boolean wrongVersion() {
		return !Loader.MC_VERSION.equals(ModInfo.mcversion);
	}
	
	private static Random r = new Random();
	
	/**
	 * Checks if Minecraft is running in a deobfuscated enviroment.
	 */
	
	public static boolean developmentEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	
	public static boolean debug = false;
}
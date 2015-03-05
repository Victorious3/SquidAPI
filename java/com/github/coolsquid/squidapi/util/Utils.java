/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.Random;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import scala.util.hashing.MurmurHash3.ArrayHashing;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.relauncher.Side;

public class Utils {
	
	/**
	 * Has a d/k chance to return true.
	 * @param d
	 * @param k
	 * @return boolean
	 */
	
	public static boolean getChance(int d, int k) {
		int a = getRandInt(1, k);
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
	
	public static int hash(String input) {
		final int prime = 31;
		int hash = 1;
		for (int a = 0; a < input.length(); a++) {
			hash = hash + (input.charAt(a) * input.charAt(a) * prime * (a + 1 * hash) * input.length());
		}
		return hash;
	}
	
	public static Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public static ModContainer getMod(String modid) {
		return Loader.instance().getIndexedModList().get(modid);
	}
	
	public static int hash(Object object) {
		return new ArrayHashing<Object>().hash(object);
	}
	
	public static void runVersionCheckerCompat(String modid, String id) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("curseProjectName", id + "-" + modid);
		tag.setString("curseFilenameParser", modid + "-[].jar");
		FMLInterModComms.sendRuntimeMessage(modid, "VersionChecker", "addCurseCheck", tag);
	}
}
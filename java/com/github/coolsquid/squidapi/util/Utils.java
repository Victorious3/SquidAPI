/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
	
	/** Blacklist to enforce Reika's third party modification rules.*/
	public static boolean doNotClearRecipes() {
		return Loader.isModLoaded("Rotarycraft") || Loader.isModLoaded("Chromaticraft");
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> newList(Object... objects) {
		ArrayList<Object> a = new ArrayList<Object>();
		for (Object object: objects) {
			a.add(object);
		}
		return (List<E>) a;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> Set<E> newSet(Object... objects) {
		HashSet<Object> a = new HashSet<Object>();
		for (Object object: objects) {
			a.add(object);
		}
		return (Set<E>) a;
	}
}
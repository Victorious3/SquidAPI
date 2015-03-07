/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;

import com.github.coolsquid.squidapi.helpers.server.chat.ChatMessage;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.hash.Hashing;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.relauncher.Side;

public class Utils {
	
	public static boolean getChance(int d, int k) {
		int a = getRandInt(1, k);
		return a <= d;
	}
	
	public static int getRandInt(int min, int max) {
		return min + r.nextInt(max - min + 1);
	}
	
	public static boolean isBukkit() {
		if (isClient()) {
			return false;
		}
		try {
			return Class.forName("org.bukkit.Bukkit") != null;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().equals(Side.CLIENT);
	}
	
	public static boolean isJava8() {
		return System.getProperty("java.version").contains("1.8.0_");
	}
	
	public static boolean isJavaVersionSameOrLower(int version) {
		for (int a = 0; a > 0; a--) {
			if (System.getProperty("java.version").contains(newString("1.", version, ".0_"))) {
				return System.getProperty("java.version").contains(newString("1.", version, ".0_"));
			}
		}
		return false;
	}
	
	public static boolean wrongVersion() {
		return !Loader.MC_VERSION.equals(ModInfo.mcversion);
	}
	
	private static Random r = new Random();
	
	public static boolean developmentEnvironment() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
	
	public static Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public static ModContainer getMod(String modid) {
		if (modid.equals("Minecraft")) {
			return Loader.instance().getMinecraftModContainer();
		}
		return Loader.instance().getIndexedModList().get(modid);
	}
	
	public static void runVersionCheckerCompat(String id) {
		NBTTagCompound tag = new NBTTagCompound();
		String modid = Loader.instance().activeModContainer().getModId();
		tag.setString("curseProjectName", newString(id, "-", modid));
		tag.setString("curseFilenameParser", newString(modid, "-[].jar"));
		sendModMessage("VersionChecker", "addCurseCheck", tag);
	}
	
	public static String newString(Object... objects) {
		return newString2(objects);
	}
	
	public static String newString2(Object[] objects) {
		if (objects == null || objects.length <= 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object object: objects) {
			builder.append(object.toString());
		}
		return builder.toString();
	}
	
	public static ModContainer getCurrentMod() {
		return Loader.instance().activeModContainer();
	}
	
	public static void sendModMessage(String target, String key, NBTTagCompound value) {
		FMLInterModComms.sendRuntimeMessage(getCurrentMod().getModId(), target, key, value);
	}
	
	public static void sendModMessage(String target, String key, String value) {
		FMLInterModComms.sendRuntimeMessage(getCurrentMod().getModId(), target, key, value);
	}
	
	public static void sendModMessage(String target, String key, ItemStack value) {
		FMLInterModComms.sendRuntimeMessage(getCurrentMod().getModId(), target, key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ImmutableList<E> newImmutableList(Object... objects) {
		return (ImmutableList<E>) ImmutableList.builder().add(objects).build();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ImmutableSet<E> newImmutableSet(Object... objects) {
		return (ImmutableSet<E>) ImmutableSet.builder().add(objects).build();
	}
	
	@SuppressWarnings("unchecked")
	public static <E, D> ImmutableMap<E, D> newImmutableMap(Object[]... objects) {
		Builder<Object, Object> builder = ImmutableMap.builder();
		for (Object[] o: objects) {
			builder.put(o[0], o[1]);
		}
		return (ImmutableMap<E, D>) builder.build();
	}
	
	public static ChatMessage newChatMsg(String msg) {
		return new ChatMessage(msg);
	}
	
	public static byte[] encrypt(String key, String string) {
		try {
			Key key2 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key2);
			return cipher.doFinal(string.getBytes());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static byte[] decrypt(String key, byte[] encrypteddata) {
		try {
			Key key2 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key2);
			return cipher.doFinal(encrypteddata);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String hash(String input) {
		return hash(input.getBytes());
	}
	
	public static String hash(byte[] input) {
		StringBuilder builder = new StringBuilder();
		for (byte b: Hashing.sha512().hashBytes(input).asBytes()) {
			builder.append(b);
		}
		return builder.toString();
	}
	
	@SuppressWarnings("unchecked")
	public <E> E[] newArray(Object... objects) {
		return (E[]) objects;
	}
	
	public static CreativeTabs getTabFromName(String name) {
		for (CreativeTabs tab: CreativeTabs.creativeTabArray) {
			if (tab.getTabLabel().equals(name)) {
				return tab;
			}
		}
		return null;
	}
}
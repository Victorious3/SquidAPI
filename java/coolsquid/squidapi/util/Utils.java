/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.io.File;
import java.net.URL;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.RegistryNamespaced;

import org.apache.commons.lang3.CharSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.formatting.WebSCFParser;
import coolsquid.squidapi.util.io.IOUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.relauncher.Side;

public class Utils {
	
	public static final String LINE = System.getProperty("line.separator");

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
		return IntUtils.parseInt(System.getProperty("java.version").charAt(2)) <= version;
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
		String modid = Loader.instance().activeModContainer().getModId();
		SquidAPI.instance().info("Running VersionChecker compatibility for ", modid, ".");
		NBTTagCompound tag = new NBTTagCompound();
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
			if (object != null) {
				builder.append(object.toString());
			}
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
	public static <E> E[] newArray(E... objects) {
		return objects;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> merge(List<E>... lists) {
		List<E> newlist = Lists.newArrayList();
		for (List<E> list: lists) {
			for (E e: list) {
				newlist.add(e);
			}
		}
		return newlist;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> clone(int amount, E object) {
		List<Object> list = Lists.newArrayList();
		for (int a = 0; a < amount; a++) {
			list.add(object);
		}
		return (List<E>) list;
	}

	public static ModContainer getModFromPackage(String string) {
		for (ModContainer mod: Loader.instance().getActiveModList()) {
			if (mod.getOwnedPackages().contains(string)) {
				return mod;
			}
		}
		return null;
	}

	public static StringBuilder builder() {
		return new StringBuilder();
	}
	
	public static Achievement getAchievement(String name) {
		for (Object a: AchievementList.achievementList) {
			Achievement b = (Achievement) a;
			if (b.statId.equals(name)) {
				return b;
			}
		}
		return null;
	}
	
	public static String newStringWithSpaces(Object... objects) {
		return newStringWithSpaces2(objects);
	}
	
	public static String newStringWithSpaces2(Object[] objects) {
		if (objects == null || objects.length <= 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object object: objects) {
			if (object != null) {
				builder.append(object.toString());
				builder.append(" ");
			}
		}
		return builder.substring(0, builder.length() - 1);
	}

	public static Random newRandom(long seed) {
		Random rand = new Random();
		rand.setSeed(seed);
		return rand;
	}
	
	public static Random newRandom() {
		return new Random();
	}
	
	public static String trim(String string, int length) {
		return string.substring(0, length);
	}

	public static String newLine() {
		return LINE;
	}
	
	public static boolean compatibleWithCharset(String string, CharSet... charsets) {
		for (char a: string.toCharArray()) {
			for (CharSet charset: charsets) {
				if (!charset.contains(a)) {
					SquidAPI.instance().info(a);
					return false;
				}
			}
		}
		return true;
	}

	public static <E, T> Map<E, T> newIterableMap() {
		return new IterableMap<E, T>();
	}

	public static String repeat(String string, int length) {
		StringBuilder a = Utils.builder();
		for (int b = 0; b < length; b++) {
			a.append(string);
		}
		return a.toString();
	}
	
	public static String repeat(char c, int length) {
		StringBuilder a = Utils.builder();
		for (int b = 0; b < length; b++) {
			a.append(c);
		}
		return a.toString();
	}
	
	public static byte[] convertToBytes(String string) {
		byte[] result = new byte[string.length()];
		int counter = 0;
		for (byte b: string.getBytes()) {
			if (counter == result.length) {
				result = Arrays.copyOf(result, result.length + 4);
			}
			result[counter++] = b;
		}
		return result;
	}

	public static String convertToString(byte[] bytes) {
		StringBuilder a = builder();
		for (byte b: bytes) {
			a.append(b);
			a.append('-');
		}
		a.deleteCharAt(a.length() - 1);
		return a.toString();
	}
	
	public static String a(String string) {
		StringBuilder result = builder();
		char[] chars = string.toCharArray();
		for (int a = 0; a < chars.length; a++) {
			result.append((chars[a] + a) * 31 * string.length());
		}
		return result.toString();
	}
	
	public static void dump(String modid, File file, RegistryNamespaced registry) {
		List<String> a = Lists.newArrayList();
		if (modid == null) {
			for (Object b: registry) {
				a.add(registry.getNameForObject(b));
			}
		}
		else {
			for (Object b: registry) {
				String c = registry.getNameForObject(b);
				if (c.startsWith(modid)) {
					a.add(c);
				}
			}
		}
		IOUtils.writeLines(file, a);
	}

	public static <E, T> int hash(Map<E, T> map) {
		int result = 1;
		for (E a: map.keySet()) {
			result += (map.get(a).hashCode() * a.hashCode());
		}
		return result;
	}

	public static List<ChatMessage> parseSWNI(URL url) {
		return new WebSCFParser(url).get();
	}

	public static String getPackName() {
		String dir = System.getProperty("user.dir").replace("\\", "/");
		if (dir.contains("/.technic/")) {
			return parseTechnicModpackName(dir);
		}
		return null;
	}

	private static String parseTechnicModpackName(String dir) {
		String[] a = dir.split("/");
		return a[a.length - 1];
	}

	public static String getString(char... b) {
		StringBuilder c = builder();
		boolean e = true;
		for (int a = 0; a < b.length; a++) {
			if (e) {
				c.append(b[a + 1]);
				e = false;
			}
			else {
				c.append(b[a - 1]);
				e = true;
			}
		}
		return c.toString();
	}

	public static String ensureNotNull(String string) {
		return string != null ? string : "";
	}
}
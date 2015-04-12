/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.RegistryNamespaced;

import org.apache.commons.lang3.CharSet;

import com.google.common.collect.Lists;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.helpers.server.chat.ChatMessage;
import coolsquid.squidapi.util.formatting.WebSCFParser;
import coolsquid.squidapi.util.io.IOUtils;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInterModComms;

public class Utils {

	public static boolean getChance(int d, int k) {
		int a = getRandInt(1, k);
		return a <= d;
	}
	
	public static int getRandInt(int min, int max) {
		return min + new Random().nextInt(max - min + 1);
	}

	@Deprecated
	public static boolean isClient() {
		return MiscLib.CLIENT;
	}

	public static boolean isJavaVersionSameOrLower(int version) {
		return IntUtils.parseInt(System.getProperty("java.version").charAt(2)) <= version;
	}

	public static boolean wrongVersion() {
		return !Loader.MC_VERSION.equals(ModInfo.mcversion);
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

	@Deprecated
	public static String newString(Object... objects) {
		return newString2(objects);
	}

	@Deprecated
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
		ModContainer mod = Loader.instance().activeModContainer();
		if (mod == null) {
			return Loader.instance().getMinecraftModContainer();
		}
		return mod;
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
	public static <E> E[] newArray(E... objects) {
		return objects;
	}

	@Deprecated
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

	@Deprecated
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

	/** Move to StringParser */
	@Deprecated
	public static Achievement getAchievement(String name) {
		for (Object a: AchievementList.achievementList) {
			Achievement b = (Achievement) a;
			if (b.statId.equals(name)) {
				return b;
			}
		}
		return null;
	}

	public static Random newRandom(long seed) {
		Random rand = new Random();
		rand.setSeed(seed);
		return rand;
	}

	/** Move to Charsets? */
	@Deprecated
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

	/** Move to IterableMap. */
	@Deprecated
	public static <E, T> Map<E, T> newIterableMap() {
		return new IterableMap<E, T>();
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

	/** Move to SWNIUtils? */
	@Deprecated
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

	public static Class<?> getCaller() {
		try {
			return Class.forName(getTrace()[3].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void printTrace() {
		printTrace(SquidAPI.instance());
	}

	public static void printTrace(SquidAPIMod mod) {
		StackTraceElement[] a = getTrace();
		for (int b = 2; b < a.length; b++) {
			StackTraceElement c = a[b];
			mod.info(c.toString());
		}
	}

	public static StackTraceElement[] getTrace() {
		return new Throwable().getStackTrace();
	}

	public static String[] newBlockNameArray(Iterable<Block> blocks) {
		List<String> result = Lists.newArrayList();
		for (Block b: blocks) {
			result.add(Block.blockRegistry.getNameForObject(b));
		}
		return result.toArray(new String[] {});
	}

	public static <E> E newInstance(Class<E> clazz, Object... args) {
		Class<?>[] args2 = new Class<?>[args.length];
		for (int a = 0; a < args.length; a++) {
			args2[a] = args[a].getClass();
		}
		try {
			return clazz.getConstructor(args2).newInstance(args);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.logging.Logger;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class MiscLib {

	public static final String LINE = System.lineSeparator();

	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final int JAVA_VERSION_NUMBER = IntUtils.parseInt(JAVA_VERSION.charAt(2));

	public static final Logger LOGGER;

	public static final SquidAPIProperties SETTINGS = new SquidAPIProperties();
	public static final SquidAPIProperties NICKNAMES = new SquidAPIProperties();

	public static final OneWaySet<ModPack> MODPACKS = new OneWaySet<ModPack>();
	public static final boolean DEV_ENVIRONMENT = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

	public static final boolean CLIENT = FMLCommonHandler.instance().getSide().equals(Side.CLIENT);
	public static final boolean SERVER = FMLCommonHandler.instance().getSide().equals(Side.SERVER);
	public static final Side SIDE = FMLCommonHandler.instance().getSide();

	public static final boolean BUKKIT;

	public static final SquidAPI SQUIDAPI;

	private static final Blacklist<String> BLACKLIST = Blacklist.newInstance("Reika", "RotaryCraft", "ReactorCraft", "ElectriCraft", "ChromatiCraft");

	public static boolean updateChecker() {
		return CLIENT && !Loader.isModLoaded("VersionChecker") && SETTINGS.getBoolean("updateChecker");
	}

	public static Blacklist<String> getBlacklist() {
		return BLACKLIST;
	}

	public static String getBlacklister(Object object) {
		String name;
		if (object instanceof Block) {
			name = Block.blockRegistry.getNameForObject(object).split(":")[0];
		}
		else if (object instanceof Item) {
			name = Item.itemRegistry.getNameForObject(object).split(":")[0];
		}
		else {
			String a = object.getClass().getName();
			name = a.substring(0, a.indexOf('.') - 1);
		}
		if (getBlacklist().contains(name)) {
			return name;
		}
		return null;
	}

	static {
		File file = new File("./logs/SquidAPI.log");
		if (file.exists()) {
			file.delete();
		}
		LOGGER = new Logger(file);

		NICKNAMES.set("Eyamaz", "Eyamapple");
		NICKNAMES.set("CoolSquid", "Squiddy");
		NICKNAMES.set("MyLittleSquiddy", "Squiddy");
		NICKNAMES.set("VictiniX888", "ToiletSeatLover");
		NICKNAMES.set("ZeldoKavira", "Zeldo");

		MODPACKS.add(new ModPack("BloodNBones", "HAAAAAAAAAAAAAAALP!", "Eyamapple"));
		MODPACKS.add(new ModPack("MagicFarm 3", "Did that chicken just kill me!?!", "Jadedcat"));

		boolean bukkit = false;
		if (SERVER) {
			try {
				Class.forName("org.bukkit.Bukkit");
				bukkit = true;
			} catch (ClassNotFoundException e) {}
		}
		BUKKIT = bukkit;

		SQUIDAPI = (SquidAPI) Loader.instance().getIndexedModList().get("SquidAPI").getMod();
	}
}
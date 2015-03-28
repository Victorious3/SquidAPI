/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;

import org.apache.logging.log4j.Level;

import com.google.common.collect.Maps;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.exception.IdException;
import cpw.mods.fml.common.FMLCommonHandler;

public class IdHelper {
	
	private static final Map<String, Integer> biomenameToId;
	private static final Map<Integer, BiomeGenBase> idToBiome = Maps.newHashMap();
	
	public static int findFreeBiomeId(String biomename) {
		if (biomenameToId.containsKey(biomename)) {
				return biomenameToId.get(biomename);
		}
		for (int a = 0; a < 256; a++) {
			BiomeGenBase b = BiomeGenBase.getBiomeGenArray()[a];
			if (b == null) {
				biomenameToId.put(biomename, a);
				return a;
			}
		}
		throw new IdException("No free biome ids!");
	}
	
	public static void registerBiome(BiomeGenBase biome) {
		idToBiome.put(biome.biomeID, biome);
	}
	
	public static void checkForConflicts() {
		for (int a = 0; a < idToBiome.size(); a++) {
			if (idToBiome.containsKey(a)) {
				BiomeGenBase b = BiomeGenBase.biomeList[a];
				BiomeGenBase c = idToBiome.get(a);
				if (b != c) {
					String d = "null";
					String e = "null";
					if (b != null) {
						d = b.biomeName;
					}
					if (c != null) {
						e = c.biomeName;
					}
					throw new IdException("Biome id conflict! Change the id of ", d, " or ", e, ".");
				}
			}
		}
	}
	
	public static int findFreeEnchantmentId() {
		for (int a = 0; a < Enchantment.enchantmentsList.length; a++) {
			Enchantment e = Enchantment.enchantmentsList[a];
			if (e == null) {
				return a;
			}
		}
		throw new IdException("No free enchantment ids!");
	}
	
	public static int findFreePotionId() {
		for (int a = 0; a < Potion.potionTypes.length; a++) {
			Potion p = Potion.potionTypes[a];
			if (p == null) {
				return a;
			}
		}
		throw new IdException("No free potion ids!");
	}
	
	public static void checkBiomeId(int id) {
		if (BiomeGenBase.getBiomeGenArray()[id] != null) {
			SquidAPI.instance().bigWarning(Level.WARN, "Found biome id conflict!");
			FMLCommonHandler.instance().exitJava(44, false);
		}
	}

	public static void saveIds() {
		try {
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./config/SquidAPI/biomes.cfg"))));
			for (String key: biomenameToId.keySet()) {
				StringBuilder builder = new StringBuilder();
				builder.append(key);
				builder.append(" : ");
				builder.append(biomenameToId.get(key) + "");
				w.write(builder.toString());
				w.newLine();
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static {
		biomenameToId = Maps.newHashMap();
		for (String string: FileHelper.readFile(new File("./config/SquidAPI/"), new File("./config/SquidAPI/biomes.cfg"))) {
			String[] strings = string.split(" : ");
			try {
				biomenameToId.put(strings[0], Integer.parseInt(strings[1]));
			} catch (Exception e) {
				throw new IdException("Error during id generation. Could not read property: \"" + string + "\".");
			}
		}
	}
}
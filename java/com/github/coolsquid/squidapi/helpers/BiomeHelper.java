/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import com.github.coolsquid.squidapi.util.Utils;

public class BiomeHelper {
	
	public static void removeBiome(BiomeGenBase biome) {
		for (BiomeType type: BiomeType.values()) {
			for (BiomeEntry entry: BiomeManager.getBiomes(type)) {
				if (entry.biome == biome) {
					BiomeManager.removeBiome(type, entry);
				}
			}
		}
	}
	
	public static int findFreeId() {
		for (int a = 0; a < BiomeGenBase.getBiomeGenArray().length; a++) {
			BiomeGenBase b = BiomeGenBase.getBiomeGenArray()[a];
			if (b == null) {
				return a;
			}
		}
		return Utils.getRandInt(32, 255);
	}
}
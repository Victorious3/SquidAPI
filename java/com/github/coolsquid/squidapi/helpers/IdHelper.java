package com.github.coolsquid.squidapi.helpers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;

import com.github.coolsquid.squidapi.exception.IdException;

public class IdHelper {
	
	public static int findFreeBiomeId() {
		for (int a = 0; a < 256; a++) {
			BiomeGenBase b = BiomeGenBase.getBiomeGenArray()[a];
			if (b == null) {
				return a;
			}
		}
		throw new IdException("No free biome ids!");
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
}
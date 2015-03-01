/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.enchantment.Enchantment;

import com.github.coolsquid.squidapi.config.ConfigHandler;
import com.github.coolsquid.squidapi.util.Utils;

public class EnchantmentHelper {
	
	public static int findFreeId() {
		for (int a = 0; a < Enchantment.enchantmentsList.length; a++) {
			Enchantment e = Enchantment.enchantmentsList[a];
			if (e == null) {
				return a;
			}
		}
		return Utils.getRandInt(32, ConfigHandler.maxPotionId);
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.enchantment.Enchantment;

import com.github.coolsquid.squidapi.exception.IdException;

public class EnchantmentHelper {
	
	public static int findFreeId() {
		for (int a = 0; a < Enchantment.enchantmentsList.length; a++) {
			Enchantment e = Enchantment.enchantmentsList[a];
			if (e == null) {
				return a;
			}
		}
		throw new IdException("No free enchantment ids!");
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.potion.Potion;

import com.github.coolsquid.squidapi.exception.IdException;

public class PotionHelper {

	public static int findFreeId() {
		for (int a = 0; a < Potion.potionTypes.length; a++) {
			Potion p = Potion.potionTypes[a];
			if (p == null) {
				return a;
			}
		}
		throw new IdException("No free potion ids!");
	}
}
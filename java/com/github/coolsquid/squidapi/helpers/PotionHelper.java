/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.util.List;

import net.minecraft.potion.Potion;

import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Lists;

public class PotionHelper {
	
	private static final List<Integer> badPotions;
	
	public static int getRandomBadPotion() {
		return badPotions.get(Utils.getRandInt(0, badPotions.size()));
	}
	
	static {
		badPotions = Lists.newArrayList();
		for (Potion potion: Potion.potionTypes) {
			if (potion.isBadEffect()) {
				badPotions.add(potion.id);
			}
		}
	}
}
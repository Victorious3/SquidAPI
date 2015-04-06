/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.google.common.collect.Lists;

import coolsquid.squidapi.util.EffectInfo;
import coolsquid.squidapi.util.Utils;

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

	public static PotionEffect newEffect(EffectInfo info) {
		return new PotionEffect(info.getId(), info.getDuration(), info.getAmplifier());
	}
}
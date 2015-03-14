/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AchievementHelper {
	
	public static void addAchievement(String name, int posX, int posY, Object icon, Achievement parent) {
		if (icon instanceof Item) {
			new Achievement(name, name, posX, posY, (Item) icon, parent).registerStat();
		}
		else if (icon instanceof Block) {
			new Achievement(name, name, posX, posY, (Block) icon, parent).registerStat();
		}
		else if (icon instanceof ItemStack) {
			new Achievement(name, name, posX, posY, (ItemStack) icon, parent).registerStat();
		}
	}
	
	public static void addAchievement(String name, int posX, int posY, Object icon) {
		if (icon instanceof Item) {
			new Achievement(name, name, posX, posY, (Item) icon, null).registerStat();
		}
		else if (icon instanceof Block) {
			new Achievement(name, name, posX, posY, (Block) icon, null).registerStat();
		}
		else if (icon instanceof ItemStack) {
			new Achievement(name, name, posX, posY, (ItemStack) icon, null).registerStat();
		}
	}
	
	public static void addTab(String name, Achievement[] achievements) {
		AchievementPage.registerAchievementPage(new AchievementPage(name, achievements));
	}

	public static Achievement getAchievement(String name) {
		for (Object a: AchievementList.achievementList) {
			Achievement b = (Achievement) a;
			if (b.statId.equals(name)) {
				return b;
			}
		}
		return null;
	}
}
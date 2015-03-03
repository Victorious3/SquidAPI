/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.FishingHooks;

import com.github.coolsquid.squidapi.reflection.ReflectionHelper;

public class FishingHelper {
	
	public static ArrayList<WeightedRandomFishable> getFish() {
		return ReflectionHelper.in(FishingHooks.class).field("fish", "fish").get();
	}
	
	public static ArrayList<WeightedRandomFishable> getJunk() {
		return ReflectionHelper.in(FishingHooks.class).field("junk", "junk").get();
	}
	
	public static ArrayList<WeightedRandomFishable> getTreasure() {
		return ReflectionHelper.in(FishingHooks.class).field("treasure", "treasure").get();
	}
	
	public static void addFish(ItemStack itemstack, int weight) {
		FishingHooks.addFish(new WeightedRandomFishable(itemstack, weight));
	}
	
	public static void addJunk(ItemStack itemstack, int weight) {
		FishingHooks.addJunk(new WeightedRandomFishable(itemstack, weight));
	}
	
	public static void addTreasure(ItemStack itemstack, int weight) {
		FishingHooks.addTreasure(new WeightedRandomFishable(itemstack, weight));
	}
}
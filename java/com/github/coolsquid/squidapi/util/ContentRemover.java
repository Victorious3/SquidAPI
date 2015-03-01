/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;

import com.github.coolsquid.squidapi.helpers.FishingHelper;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.google.common.collect.ImmutableSet;

import cpw.mods.fml.common.Loader;

public class ContentRemover {

	private static final ArrayList<Item> recipesToRemove = new ArrayList<Item>();
	private static final HashSet<String> blacklist = new HashSet<String>();
	
	public static void blacklist(String... mods) {
		for (String mod: mods) {
			blacklist.add(mod);
		}
	}
	
	public static Set<String> getBlacklist() {
		return ImmutableSet.copyOf(blacklist);
	}
	
	public static boolean isBlacklistedModLoaded() {
		for (String mod: getBlacklist()) {
			if (Loader.isModLoaded(mod)) {
				return true;
			}
		}
		return false;
	}
	
	public static void remove(String name, ContentType type) {
		for (String mod: blacklist) {
			if (name.startsWith(mod + ":")) {
				String content = type.toString() + " " + name;
				LogHelper.warn(mod + " has requested to be blacklisted from content removal. " + content + " will not be removed.");
				return;
			}
		}
		if (type == ContentType.RECIPE) {
			recipesToRemove.add((Item) Item.itemRegistry.getObject(name));
		}
		else if (type == ContentType.ENCHANTMENT) {
			for (String mod: getBlacklist()) {
				Enchantment e = Enchantment.enchantmentsList[Integer.parseInt(name)];
				if (e != null && e.getClass().getName().contains(mod)) {
					LogHelper.warn(mod + " has requested to be blacklisted from content removal. Enchantment ", name, " will not be removed.");
					return;
				}
			}
			Enchantment.enchantmentsList[Integer.parseInt(name)] = null;
		}
		else if (type == ContentType.POTION) {
			for (String mod: getBlacklist()) {
				Potion e = Potion.potionTypes[Integer.parseInt(name)];
				if (e != null && e.getClass().getName().contains(mod)) {
					LogHelper.warn(mod + " has requested to be blacklisted from content removal. Potion ", name, " will not be removed.");
					return;
				}
			}
			Potion.potionTypes[Integer.parseInt(name)] = null;
		}
		else if (type == ContentType.FISH) {
			ArrayList<WeightedRandomFishable> fish = FishingHelper.getFish();
			for (int a = 0; a < fish.size(); a++) {
				WeightedRandomFishable fishable = fish.get(a);
				if (fishable.func_150708_a(new Random()).getItem() == Item.itemRegistry.getObject(name)) {
					fish.remove(a);
				}
			}
		}
		else if (type == ContentType.JUNK) {
			ArrayList<WeightedRandomFishable> junk = FishingHelper.getJunk();
			for (int a = 0; a < junk.size(); a++) {
				WeightedRandomFishable fishable = junk.get(a);
				if (fishable.func_150708_a(new Random()).getItem() == Item.itemRegistry.getObject(name)) {
					junk.remove(a);
				}
			}
		}
		else if (type == ContentType.TREASURE) {
			ArrayList<WeightedRandomFishable> treasure = FishingHelper.getTreasure();
			for (int a = 0; a < treasure.size(); a++) {
				WeightedRandomFishable fishable = treasure.get(a);
				if (fishable.func_150708_a(new Random()).getItem() == Item.itemRegistry.getObject(name)) {
					treasure.remove(a);
				}
			}
		}
		else if (type == ContentType.DUNGEONMOB) {
			DungeonHooks.removeDungeonMob(name);
		}
		else if (type == ContentType.CHESTGEN) {
			String[] gg = name.split(";");
			ChestGenHooks.removeItem(gg[0], new ItemStack((Item) Item.itemRegistry.getObject(gg[1])));
		}
		else if (type == ContentType.BIOME) {
			BiomeGenBase.biomeList[Integer.parseInt(name)] = null;
		}
	}
	
	public enum ContentType {
		RECIPE,
		ENCHANTMENT,
		POTION,
		FISH,
		JUNK,
		TREASURE,
		DUNGEONMOB,
		CHESTGEN,
		BIOME;
	}
	
	/**
	 * Removes recipes for all blocks in the recipesToRemove list.
	 */
	
	public static void removeContent() {
		if (!recipesToRemove.isEmpty()) {
			for (int a = 0; a < CraftingManager.getInstance().getRecipeList().size(); a++) {
				IRecipe r = (IRecipe) CraftingManager.getInstance().getRecipeList().get(a);
				for (int b = 0; b < recipesToRemove.size(); b++) {
					Item i = recipesToRemove.get(b);
					if (i != null && r != null && r.getRecipeOutput() != null) {
						if (r.getRecipeOutput().getItem() == i) {
							CraftingManager.getInstance().getRecipeList().remove(a);
						}
					}
				}
			}
		}
	}
}
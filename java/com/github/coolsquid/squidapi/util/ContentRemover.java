/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;

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
	
	public static void remove(String name, ContentType contenttype) {
		for (String mod: blacklist) {
			if (name.startsWith(mod + ":")) {
				String content = contenttype.toString() + " " + name;
				LogHelper.warn(mod + " has requested to be blacklisted from content removal. " + content + " will not be removed.");
				return;
			}
		}
		if (contenttype == ContentType.RECIPE) {
			recipesToRemove.add((Item) Item.itemRegistry.getObject(name));
		}
		else if (contenttype == ContentType.ENCHANTMENT) {
			for (String mod: getBlacklist()) {
				Enchantment e = Enchantment.enchantmentsList[Integer.parseInt(name)];
				if (e != null && e.getClass().getName().contains(mod)) {
					LogHelper.warn(mod + " has requested to be blacklisted from content removal. Enchantment ", name, " will not be removed.");
					return;
				}
			}
			Enchantment.enchantmentsList[Integer.parseInt(name)] = null;
		}
		else if (contenttype == ContentType.POTION) {
			for (String mod: getBlacklist()) {
				Potion e = Potion.potionTypes[Integer.parseInt(name)];
				if (e != null && e.getClass().getName().contains(mod)) {
					LogHelper.warn(mod + " has requested to be blacklisted from content removal. Potion ", name, " will not be removed.");
					return;
				}
			}
			Potion.potionTypes[Integer.parseInt(name)] = null;
		}
	}
	
	public enum ContentType {
		RECIPE,
		ENCHANTMENT,
		POTION;
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
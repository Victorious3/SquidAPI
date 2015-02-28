/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.google.common.collect.ImmutableList;

public class ContentRemover {

	private static final ArrayList<Item> recipesToRemove = new ArrayList<Item>();
	private static final ArrayList<Integer> enchantmentsToRemove = new ArrayList<Integer>();
	private static final ArrayList<Integer> potionsToRemove = new ArrayList<Integer>();
	private static final ArrayList<String> blacklist = new ArrayList<String>();
	
	public static void blacklist(String... mods) {
		for (String mod: mods) {
			blacklist.add(mod);
		}
	}
	
	public static List<String> getBlacklist() {
		return ImmutableList.copyOf(blacklist);
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
			Item item = (Item) Item.itemRegistry.getObject(name);
			for (String mod: getBlacklist()) {
				if (item.getClass().getName().startsWith(mod)) {
					LogHelper.warn(name.split(":")[0] + " has requested to be blacklisted from content removal. " + name + " will not be removed.");
					return;
				}
			}
			recipesToRemove.add(item);
		}
		else if (contenttype == ContentType.ENCHANTMENT) {
			enchantmentsToRemove.add(Integer.parseInt(name));
		}
		else if (contenttype == ContentType.POTION) {
			potionsToRemove.add(Integer.parseInt(name));
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
		if (!enchantmentsToRemove.isEmpty()) {
			for (int a: enchantmentsToRemove) {
				Enchantment.enchantmentsList[a] = null;
			}
		}
		if (!potionsToRemove.isEmpty()) {
			for (int a: potionsToRemove) {
				Potion.potionTypes[a] = null;
			}
		}
	}
}
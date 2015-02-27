/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;

public class ContentRemover {
	
	private static final ArrayList<Item> recipesToRemove = new ArrayList<Item>();
	public static final ArrayList<Integer> enchantmentsToRemove = new ArrayList<Integer>();
	public static final ArrayList<Integer> potionsToRemove = new ArrayList<Integer>();
	public static final ArrayList<String> blacklist = new ArrayList<String>();
	
	public static void remove(String name, ContentType contenttype) {
		for (String mod: blacklist) {
			if (name.startsWith(mod + ":")) {
				return;
			}
		}
		if (contenttype == ContentType.CRAFTING) {
			recipesToRemove.add((Item) Item.itemRegistry.getObject(name));
		}
		else if (contenttype == ContentType.ENCHANTMENT) {
			enchantmentsToRemove.add(Integer.parseInt(name));
		}
		else if (contenttype == ContentType.POTION) {
			potionsToRemove.add(Integer.parseInt(name));
		}
	}
	
	public enum ContentType {
		CRAFTING,
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
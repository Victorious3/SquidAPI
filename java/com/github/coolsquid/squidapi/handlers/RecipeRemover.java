/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.registry.SimpleRegistry;

public class RecipeRemover {
	
	public static final SimpleRegistry recipesToRemove = new SimpleRegistry();
	
	/**
	 * Removes recipes for all blocks in the recipesToRemove list.
	 */
	
	public static final void removeRecipes() {
		if (recipesToRemove.size() != 0) {
			LogHelper.info("Removing recipes...");
			for (int a = 0; a < CraftingManager.getInstance().getRecipeList().size(); a++) {
				IRecipe r = (IRecipe) CraftingManager.getInstance().getRecipeList().get(a);
				try {
					for (int b = 0; b < recipesToRemove.size(); b++) {
						String i = (String) recipesToRemove.get(b);
						if (r.getRecipeOutput().getItem().getUnlocalizedName().equals(i)) {
							CraftingManager.getInstance().getRecipeList().remove(a);
						}
					}
				} catch (NullPointerException e) {}
			}
			LogHelper.info("Finished recipe removal.");
		}
	}
}
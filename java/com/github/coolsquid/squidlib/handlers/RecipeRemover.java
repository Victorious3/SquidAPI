package com.github.coolsquid.squidlib.handlers;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import com.github.coolsquid.squidlib.helpers.LogHelper;
import com.github.coolsquid.squidlib.registry.Registry;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class RecipeRemover {
	
	public static final Registry recipesToRemove = new Registry();
	
	/**
	 * Removes recipes for all blocks in the recipesToRemove list.
	 */
	
	public static final void removeRecipes() {
		int a = 0;
		int b = 0;
		if (recipesToRemove.size() != 0) {
			LogHelper.info("Removing recipes...");
			while (a < CraftingManager.getInstance().getRecipeList().size()) {
				IRecipe r = (IRecipe) CraftingManager.getInstance().getRecipeList().get(a);
				try {
					while (b < recipesToRemove.size()) {
						String i = (String) recipesToRemove.get(b);
						if (r.getRecipeOutput().getItem().getUnlocalizedName().equals(i)) {
							CraftingManager.getInstance().getRecipeList().remove(a);
						}
						b++;
					}
				} catch (NullPointerException e) {}
				a++;
				b = 0;
			}
			LogHelper.info("Finished recipe removal.");
		}
	}
}
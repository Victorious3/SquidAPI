/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.io.File;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Lists;

public class RecipeHelper {
	
	public static void dumpAllRecipes(File file) {
		List<String> list = Lists.newArrayList();
		for (Object a: CraftingManager.getInstance().getRecipeList()) {
			if (a instanceof ShapedRecipes) {
				ShapedRecipes b = (ShapedRecipes) a;
				StringBuilder c = Utils.builder();
				c.append(Item.itemRegistry.getNameForObject(b.getRecipeOutput().getItem()));
				c.append(" = ");
				for (ItemStack d: b.recipeItems) {
					if (d != null) {
						c.append(Item.itemRegistry.getNameForObject(d.getItem()));
						c.append(", ");
					}
				}
				String d = c.substring(0, c.length() - 2);
				list.add(d);
			}
			else if (a instanceof ShapelessRecipes) {
				ShapelessRecipes b = (ShapelessRecipes) a;
				StringBuilder c = Utils.builder();
				c.append(Item.itemRegistry.getNameForObject(b.getRecipeOutput().getItem()));
				c.append(" = ");
				for (Object d: b.recipeItems) {
					ItemStack e = (ItemStack) d;
					if (d != null) {
						c.append(Item.itemRegistry.getNameForObject(e.getItem()));
						c.append(", ");
					}
				}
				String d = c.substring(0, c.length() - 2);
				list.add(d);
			}
			else {
				IRecipe b = (IRecipe) a;
				if (b.getRecipeOutput() != null) {
					list.add(Item.itemRegistry.getNameForObject(b.getRecipeOutput().getItem()));
				}
			}
		}
		FileHelper.writeFile(file, list);
	}
	
	public static void dumpAllSmeltingRecipes(File file) {
		List<String> list = Lists.newArrayList();
		for (Object a: FurnaceRecipes.smelting().getSmeltingList().keySet()) {
			ItemStack b = (ItemStack) a;
			ItemStack c = (ItemStack) FurnaceRecipes.smelting().getSmeltingList().get(b);
			list.add(Utils.newString(Item.itemRegistry.getNameForObject(b.getItem()), " = ", Item.itemRegistry.getNameForObject(c.getItem())));
		}
		FileHelper.writeFile(file, list);
	}
}
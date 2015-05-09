/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.io.BufferedWriter;
import java.io.File;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import coolsquid.squidapi.util.StringUtils;
import coolsquid.squidapi.util.io.IOUtils;

public class RecipeHelper {

	public static void dumpAllRecipes(File file) {
		try {
			BufferedWriter w = IOUtils.newWriter(file);
			for (Object a: CraftingManager.getInstance().getRecipeList()) {
				if (a instanceof ShapedRecipes) {
					ShapedRecipes b = (ShapedRecipes) a;
					StringBuilder c = StringUtils.builder();
					c.append(Item.itemRegistry.getNameForObject(b.getRecipeOutput().getItem()));
					c.append(" = ");
					for (ItemStack d: b.recipeItems) {
						if (d != null) {
							c.append(Item.itemRegistry.getNameForObject(d.getItem()));
							c.append(", ");
						}
					}
					String d = c.substring(0, c.length() - 2);
					w.write(d);
					w.newLine();
				}
				else if (a instanceof ShapelessRecipes) {
					ShapelessRecipes b = (ShapelessRecipes) a;
					StringBuilder c = StringUtils.builder();
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
					w.write(d);
					w.newLine();
				}
				else {
					IRecipe b = (IRecipe) a;
					if (b.getRecipeOutput() != null) {
						w.write(Item.itemRegistry.getNameForObject(b.getRecipeOutput().getItem()));
						w.newLine();
					}
				}
			}
			w.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void dumpAllSmeltingRecipes(File file) {
		try {
			BufferedWriter w = IOUtils.newWriter(file);
			for (Object a: FurnaceRecipes.smelting().getSmeltingList().keySet()) {
				ItemStack b = (ItemStack) a;
				ItemStack c = (ItemStack) FurnaceRecipes.smelting().getSmeltingList().get(b);
				w.write(Item.itemRegistry.getNameForObject(b.getItem()) + " = " + Item.itemRegistry.getNameForObject(c.getItem()));
				w.newLine();
			}
			w.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.compat.EnumBlacklist;
import coolsquid.squidapi.helpers.FishingHelper;
import coolsquid.squidapi.helpers.VillageHelper;
import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.Loader;

public class ContentRemover {

	private static final List<Item> recipesToRemove = new ArrayList<Item>();
	private static final Set<Item> furnaceRecipesToRemove = Sets.newHashSet();

	public static boolean isBlacklistedModLoaded() {
		for (EnumBlacklist entry: MiscLib.getBlacklist()) {
			if (Loader.isModLoaded(entry.getName())) {
				return true;
			}
		}
		return false;
	}

	public static void remove(String name, ContentType type) {
		if (MiscLib.getBlacklister(name) != null) {
			String content = type.toString() + " " + name;
			SquidAPI.instance().warn(MiscLib.getBlacklister(name) + " has requested to be blacklisted from content removal. " + content + " will not be removed.");
			return;
		}
		if (type == ContentType.RECIPE) {
			recipesToRemove.add((Item) Item.itemRegistry.getObject(name));
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
			ChestGenHooks.removeItem(gg[0], StringParser.parseItemStack(gg[1]));
		}
		else if (type == ContentType.PROFESSION) {
			VillageHelper.professionstoremove.add(IntUtils.parseInt(name));
		}
		else if (type == ContentType.SMELTING) {
			furnaceRecipesToRemove.add((Item) Item.itemRegistry.getObject(name));
		}

		SquidAPI.instance().info("Removed " + type + ' ' + name + '.');
	}

	public enum ContentType {
		RECIPE,
		FISH,
		JUNK,
		TREASURE,
		DUNGEONMOB,
		CHESTGEN,
		PROFESSION,
		SMELTING;
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
		if (!furnaceRecipesToRemove.isEmpty()) {
			@SuppressWarnings("unchecked")
			Map<ItemStack, ItemStack> recipes = Maps.newHashMap(FurnaceRecipes.smelting().getSmeltingList());
			for (ItemStack input: recipes.keySet()) {
				ItemStack output = recipes.get(input);
				if (furnaceRecipesToRemove.contains(output.getItem())) {
					FurnaceRecipes.smelting().getSmeltingList().remove(input);
				}
			}
		}
	}
}
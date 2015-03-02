/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;

public class StringParser {
	
	public static Item parseItem(String string) {
		return (Item) Item.itemRegistry.getObject(string);
	}
	
	public static Block parseBlock(String string) {
		return (Block) Block.blockRegistry.getObject(string);
	}
	
	public static ItemStack parseItemStack(String string) {
		String[] s = string.split(";");
		int amount = 1;
		if (s.length == 2) {
			amount = Integer.parseInt(s[1]);
		}
		return new ItemStack(parseItem(s[0]), amount);
	}
	
	public static EnumDifficulty parseDifficulty(String string) {
		EnumDifficulty difficulty = null;
		for (EnumDifficulty d: EnumDifficulty.values()) {
			if ((d + "").toLowerCase().contains(string.toLowerCase())) {
				difficulty = d;
			}
		}
		return difficulty;
	}
	
	public static Object parseInput(String string) {
		if (Item.itemRegistry.containsKey(string)) {
			return parseItemStack(string);
		}
		return string;
	}
}
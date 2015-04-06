/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.WorldType;
import coolsquid.squidapi.registry.WorldTypeRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

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
			amount = IntUtils.parseInt(s[1]);
		}
		return new ItemStack(parseItem(s[0]), amount);
	}
	
	public static Object parseInput(String string) {
		if (Item.itemRegistry.containsKey(string)) {
			return parseItemStack(string);
		}
		return string;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E parseEnumEntry(Class<?> enum2, String string) {
		try {
			return (E) enum2.getField(string).get(null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ModContainer parseMod(String modid) {
		if (modid.equals("Minecraft")) {
			return Loader.instance().getMinecraftModContainer();
		}
		for (ModContainer mod: Loader.instance().getModList()) {
			if (mod.getModId().equals(modid)) {
				return mod;
			}
		}
		return null;
	}
	
	public static CreativeTabs parseCreativeTab(String string) {
		for (CreativeTabs tab: CreativeTabs.creativeTabArray) {
			if (tab.getTabLabel().equals(string)) {
				return tab;
			}
		}
		return null;
	}
	
	public static Potion parsePotion(String string) {
		for (Potion potion: Potion.potionTypes) {
			if (potion != null && potion.getName().equals(string)) {
				return potion;
			}
		}
		return null;
	}

	public static WorldType parseWorldType(String string) {
		return WorldTypeRegistry.instance().get(string);
	}

	public static Enchantment parseEnchantment(String string) {
		for (Enchantment a: Enchantment.enchantmentsList) {
			if (a != null && a.getName().equals(string)) {
				return a;
			}
		}
		return null;
	}
}
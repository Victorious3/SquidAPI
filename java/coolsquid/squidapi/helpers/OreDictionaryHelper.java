/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

import coolsquid.squidapi.exception.SquidAPIException;
import coolsquid.squidapi.reflection.ReflectionHelper;
import coolsquid.squidapi.util.io.IOUtils;

public class OreDictionaryHelper {

	private static final ReflectionHelper r = ReflectionHelper.in(OreDictionary.class);

	public static final List<String> idToName() {
		return r.field("idToName", "idToName").get();
	}

	public static final Map<String, Integer> nameToId() {
		return r.field("nameToId", "nameToId").get();
	}

	public static void removeEntry(String name) {
		idToName().remove(name);
		nameToId().remove(name);
	}

	public static void registerOres(String name, Object... ores) {
		for (Object ore: ores) {
			if (ore instanceof Item) {
				OreDictionary.registerOre(name, (Item) ore);
			}
			else if (ore instanceof Block) {
				OreDictionary.registerOre(name, (Block) ore);
			}
			else if (ore instanceof ItemStack) {
				OreDictionary.registerOre(name, (ItemStack) ore);
			}
			else {
				throw new SquidAPIException("The ore must be an Item, Block or an ItemStack!");
			}
		}
	}

	public static void dumpOres(File file) {
		List<String> list = Lists.newArrayList();
		for (String name: OreDictionary.getOreNames()) {
			list.add(name);
		}
		IOUtils.writeLines(file, (Object[]) OreDictionary.getOreNames());
	}
}
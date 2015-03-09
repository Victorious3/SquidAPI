/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.util.List;
import java.util.Map;

import net.minecraftforge.oredict.OreDictionary;

import com.github.coolsquid.squidapi.reflection.ReflectionHelper;

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
}
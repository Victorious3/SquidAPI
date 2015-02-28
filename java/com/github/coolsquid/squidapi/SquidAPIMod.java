/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.util.HashMap;

import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModMetadata;

public class SquidAPIMod {
	
	private static final HashMap<String, ModMetadata> meta = new HashMap<String, ModMetadata>();

	public SquidAPIMod(String desc) {
		ModMetadata meta = Loader.instance().activeModContainer().getMetadata();
		meta.autogenerated = false;
		meta.authorList = Utils.newList("CoolSquid");
		meta.url = "http://coolsquid.wix.com/software";
		meta.description = desc;
		meta.credits = "CoolSquid";
	}
	
	public static ModMetadata getMetadata(String mod) {
		return meta.get(mod);
	}
}
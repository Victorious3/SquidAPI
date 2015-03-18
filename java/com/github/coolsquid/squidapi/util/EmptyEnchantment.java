/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EmptyEnchantment extends Enchantment {

	public EmptyEnchantment(int id) {
		super(id, 0, EnumEnchantmentType.all);
		this.name = Utils.newString("empty", id);
	}
	
	public static void replaceEnchantment(int id) {
		Enchantment.enchantmentsList[id] = null;
		new EmptyEnchantment(id);
	}
}
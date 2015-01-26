/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.creativetab;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class SquidCreativeTabs {
	public static final ITab squidTab = new ITab("SquidTab", Item.getItemFromBlock(Blocks.water));
}
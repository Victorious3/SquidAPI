/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ITab extends CreativeTabs {
	
	private Item it;
	
	public ITab(String label, Item icon) {
		super(label);
		it = icon;
	}
	
	@Override
	public Item getTabIconItem() {
		return it;
	}
	
	public void add(Object[] items) {
		int a = 0;
		try {
			while (items[a] != null) {
				if (items[a] instanceof Item)
					((Item) items[a]).setCreativeTab(this);
				if (items[a] instanceof Block)
					((Block) items[a]).setCreativeTab(this);
				a++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
	
	public void remove(Object[] items) {
		int a = 0;
		try {
			while (items[a] != null) {
				if (items[a] instanceof Item)
					((Item) items[a]).setCreativeTab(null);
				if (items[a] instanceof Block)
					((Block) items[a]).setCreativeTab(null);
				a++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
}

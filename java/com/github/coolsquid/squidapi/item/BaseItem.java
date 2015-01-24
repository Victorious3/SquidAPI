package com.github.coolsquid.squidapi.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.github.coolsquid.squidapi.logging.Logger;
import com.github.coolsquid.squidapi.registry.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

public class BaseItem extends Item {
	
	public static final Registry itemRegistry = new Registry();
	
	public boolean isRepairable = false;
	
	public BaseItem(String name, CreativeTabs tab) {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(name);
		setCreativeTab(tab);
		itemRegistry.register(this);
	}
	
	public int getMaxStackSize() {
		return maxStackSize;
	}
	
	@Override
	public boolean isRepairable() {
		return isRepairable;
	}
	
	public static void dumpItemNames() {
		Logger logger = new Logger("", "SquidItems");
		for (int a = 0; a < itemRegistry.size(); a++) {
			logger.log(((BaseItem) itemRegistry.getObjectFromId(a)).getUnlocalizedName());
		}
		logger.save(false);
	}
}

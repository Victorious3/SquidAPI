package com.github.coolsquid.squidlib.item;

import net.minecraft.item.Item;

import com.github.coolsquid.squidlib.creativetab.SquidCreativeTabs;
import com.github.coolsquid.squidlib.logging.Logger;
import com.github.coolsquid.squidlib.registry.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

public class BaseItem extends Item {
	
	public static final Registry itemRegistry = new Registry();
	
	public boolean isRepairable = false;
	
	public BaseItem(String name) {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		setTextureName(name);
		setCreativeTab(SquidCreativeTabs.squidTab);
		itemRegistry.register(this, name);
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
			logger.log(((BaseItem) itemRegistry.getObjectFromName(itemRegistry.getNameFromId(a))).getUnlocalizedName());
		}
		logger.save(false);
	}
}
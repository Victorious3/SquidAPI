/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.item;

import net.minecraft.item.Item;
import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBasic extends Item {
	
	public boolean isRepairable = false;
	
	public ItemBasic(String name) {
		String modid = Utils.getCurrentMod().getModId();
		SquidAPI.instance().info("Registering item ", name, " from ", modid, ".");
		this.setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
		this.setTextureName(Utils.newString(modid, ":", name));
	}
	
	public int getMaxStackSize() {
		return this.maxStackSize;
	}
	
	@Override
	public boolean isRepairable() {
		return this.isRepairable;
	}
}
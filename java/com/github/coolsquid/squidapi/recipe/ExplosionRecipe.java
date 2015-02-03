package com.github.coolsquid.squidapi.recipe;

import java.util.HashMap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class ExplosionRecipe {
	
	public static final HashMap<Object, Object> recipes = new HashMap<Object, Object>();
	
	@SubscribeEvent
	public void onTossItem(ItemTossEvent event) {
		if (event.player.getHeldItem() != null && recipes.containsKey(event.player.getHeldItem().getItem())) {
			event.player.worldObj.createExplosion(event.player, event.player.posX, event.player.posY, event.player.posZ, 5, false);
			event.player.dropPlayerItemWithRandomChoice(new ItemStack((Item) recipes.get(event.player.getHeldItem().getItem())), true);
		}
	}
}
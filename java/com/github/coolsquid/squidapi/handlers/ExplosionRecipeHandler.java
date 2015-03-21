/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.github.coolsquid.squidapi.recipe.ExplosionRecipe;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExplosionRecipeHandler {
	
	@SubscribeEvent
	public void onItemJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityItem) {
			if (ExplosionRecipe.recipes.containsKey(((EntityItem) event.entity).getEntityItem().getItem())) {
				ExplosionRecipe recipe = ExplosionRecipe.recipes.get(((EntityItem) event.entity).getEntityItem().getItem());
				ItemStack item = recipe.getOutput();
				event.world.newExplosion(event.entity, event.entity.posX, event.entity.posY, event.entity.posZ, recipe.getStrength(), false, true);
				item.stackSize = ((EntityItem) event.entity).getEntityItem().stackSize;
				EntityItem itemstack = new EntityItem(event.world, event.entity.posX, event.entity.posY, event.entity.posZ, item);
				event.world.spawnEntityInWorld(itemstack);
				event.entity.setDead();
			}
		}
	}
}
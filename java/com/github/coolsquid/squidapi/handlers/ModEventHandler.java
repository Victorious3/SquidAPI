/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.github.coolsquid.squidapi.item.ItemBasic;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {
	
	@SubscribeEvent
	public void onEntityItemJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityItem) {
			if (((EntityItem) event.entity).getEntityItem().getItem() instanceof ItemBasic) {
				((ItemBasic) ((EntityItem) event.entity).getEntityItem().getItem()).onEntityItemJoinWorld((EntityItem) event.entity);
			}
		}
	}
}
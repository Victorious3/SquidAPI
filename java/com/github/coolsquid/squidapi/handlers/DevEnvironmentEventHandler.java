/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DevEnvironmentEventHandler {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void playerUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			((EntityPlayer) event.entity).capabilities.setPlayerWalkSpeed(0.3F);
			((EntityPlayer) event.entity).capabilities.setFlySpeed(0.3F);
		}
	}
}
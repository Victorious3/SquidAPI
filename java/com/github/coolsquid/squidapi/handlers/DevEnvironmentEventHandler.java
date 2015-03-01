/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DevEnvironmentEventHandler {
	
	public static boolean speedy = true;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void playerUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (!speedy) {
				((EntityPlayer) event.entity).capabilities.setPlayerWalkSpeed(0.1F);
				((EntityPlayer) event.entity).capabilities.setFlySpeed(0.05F);
				return;
			}
			((EntityPlayer) event.entity).capabilities.setPlayerWalkSpeed(0.3F);
			((EntityPlayer) event.entity).capabilities.setFlySpeed(0.3F);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			((GuiMainMenu) event.gui).splashText = "";
		}
	}
}
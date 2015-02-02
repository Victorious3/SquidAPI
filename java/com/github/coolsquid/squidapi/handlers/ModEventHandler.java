/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.github.coolsquid.squidapi.item.ItemBasic;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHandler {
	
	@SubscribeEvent
	public void onEntityItemJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityItem) {
			if (((EntityItem) event.entity).getEntityItem().getItem() instanceof ItemBasic) {
				((ItemBasic) ((EntityItem) event.entity).getEntityItem().getItem()).onEntityItemJoinWorld((EntityItem) event.entity);
			}
		}
	}
	
	@SubscribeEvent
	public void nick(PlayerEvent.NameFormat event) {
		if (event.username.equals("(TheCoolSquid|TheCrazyBoy321)") || Utils.developmentEnvironment) {
			event.displayname = "CoolSquid";
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			if (Utils.getChance(1, 10)) {
				ReflectionHelper.replaceField(GuiMainMenu.class, event.gui, "splashText", "The squids will take over!");
			}
		}
	}
}
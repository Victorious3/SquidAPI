/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.github.coolsquid.squidapi.SquidAPI;
import com.github.coolsquid.squidapi.reflection.ReflectionHelper;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHandler {
	
	@SubscribeEvent
	public void nick(PlayerEvent.NameFormat event) {
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			if (SquidAPI.isLocked()) {
				ReflectionHelper.replaceField(GuiMainMenu.class, event.gui, "splashText", "field_73975_c", "§4SquidAPI has detected mods from an illegal website!");
			}
			else if (Utils.getChance(1, 10)) {
				ReflectionHelper.replaceField(GuiMainMenu.class, event.gui, "splashText", "field_73975_c", "The squids will take over!");
			}
		}
	}
}
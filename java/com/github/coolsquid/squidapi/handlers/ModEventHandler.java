/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import com.github.coolsquid.squidapi.registry.DamageSourceRegistry;
import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			if (Utils.getChance(1, 50)) {
				((GuiMainMenu) event.gui).splashText = "The squids will take over!";
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (!DamageSourceRegistry.instance().containsValue(event.source)) {
			DamageSourceRegistry.instance().register(event.source);
		}
	}
}
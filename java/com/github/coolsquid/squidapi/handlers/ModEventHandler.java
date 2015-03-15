/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

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
	public void onEntityJoin(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityVillager) {
			EntityVillager villager = (EntityVillager) event.entity;
			villager.tasks.addTask(2, new EntityAIAttackOnCollide(villager, EntityPlayer.class, 1.0D, false));
			villager.tasks.addTask(8, new EntityAIWatchClosest(villager, EntityPlayer.class, 8.0F));
			villager.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
			villager.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		}
	}
}
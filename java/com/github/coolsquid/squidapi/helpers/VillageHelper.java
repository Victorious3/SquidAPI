/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers;

import java.util.HashSet;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import com.github.coolsquid.squidapi.util.Utils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class VillageHelper {
	
	public static final HashSet<Integer> professionstoremove = new HashSet<Integer>();
	
	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event) {
		if (!professionstoremove.isEmpty() && event.entity instanceof EntityVillager) {
			EntityVillager villager = (EntityVillager) event.entity;
			while (professionstoremove.contains(villager.getProfession())) {
				int a = Utils.getRandInt(0, VillagerRegistry.getRegisteredVillagers().size());
				villager.setProfession(a);
			}
		}
	}
}
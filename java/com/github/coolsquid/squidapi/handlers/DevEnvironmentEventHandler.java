package com.github.coolsquid.squidapi.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DevEnvironmentEventHandler {
	
	@SubscribeEvent
	public void playerUpdate(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			((EntityPlayer) event.entity).capabilities.setPlayerWalkSpeed(0.3F);
			((EntityPlayer) event.entity).capabilities.setFlySpeed(0.5F);
		}
	}
}
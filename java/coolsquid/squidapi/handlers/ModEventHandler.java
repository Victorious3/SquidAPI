/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import coolsquid.squidapi.annotation.DevOnly;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiInit(InitGuiEvent event) {
		if (event.gui instanceof GuiMainMenu && MiscLib.SETTINGS.getBoolean("easterEggs")) {
			if (Utils.getChance(1, 50)) {
				((GuiMainMenu) event.gui).splashText = "The squids will take over!";
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoin(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntitySquid && MiscLib.SETTINGS.getBoolean("easterEggs") && Utils.getChance(1, 30)) {
			EntitySquid squid = (EntitySquid) event.entity;
			if (!squid.hasCustomNameTag()) {
				squid.setCustomNameTag("Squiddy");
			}
		}
	}

	public static boolean speedy = true;

	@DevOnly
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

	@DevOnly
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu) {
			((GuiMainMenu) event.gui).splashText = "";
		}
	}
}
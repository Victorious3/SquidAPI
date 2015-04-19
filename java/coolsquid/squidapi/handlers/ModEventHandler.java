/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import coolsquid.squidapi.registry.DamageSourceRegistry;
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
	public void onHurt(LivingHurtEvent event) {
		if (!DamageSourceRegistry.INSTANCE.containsValue(event.source) && !DamageSourceRegistry.INSTANCE.containsName(event.source.damageType)) {
			DamageSourceRegistry.INSTANCE.register(event.source);
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
}
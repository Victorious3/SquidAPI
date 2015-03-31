/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import coolsquid.squidapi.registry.DamageSourceRegistry;
import coolsquid.squidapi.util.EasterEggUtils;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModEventHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && MiscLib.SETTINGS.getBoolean("easterEggs")) {
			/*if (Utils.getChance(1, 50)) {
				((GuiMainMenu) event.gui).splashText = "The squids will take over!";
			}*/
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (!DamageSourceRegistry.instance().containsName(event.source.damageType)) {
			DamageSourceRegistry.instance().register(event.source);
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

	@SubscribeEvent
	public void onRender(LivingUpdateEvent event) {
		if (EasterEggUtils.APRIL_FOOLS && event.entity instanceof EntitySquid && Utils.getChance(1, 200)) {
			Entity entity = event.entity;
			World world = entity.worldObj;
			world.spawnEntityInWorld(new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.egg)));
		}
	}
}
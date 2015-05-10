/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

import org.apache.logging.log4j.message.Message;

import com.google.common.collect.ImmutableSet;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.annotation.DevOnly;
import coolsquid.squidapi.handlers.ShutdownHandler.ShutdownEvent;
import coolsquid.squidapi.helpers.OreDictionaryHelper;
import coolsquid.squidapi.util.MiscLib;
import coolsquid.squidapi.util.Utils;
import cpw.mods.fml.common.Optional;
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

	private final Set<String> oredictEntriesToRemove = ImmutableSet.of("greggy_greg_do_please_kindly_stuff_a_sock_in_it");

	@Optional.Method(modid = "MineFactoryReloaded|CompatIC2")
	@SubscribeEvent
	public void onOredictRegistration(OreRegisterEvent event) {
		if (this.oredictEntriesToRemove.contains(event.Name)) {
			OreDictionaryHelper.removeEntry(event.Name);
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onShutdown(ShutdownEvent event) {
		if (Utils.getChance(1, 10)) {
			SquidAPI.instance().info("Have a nice day!");
		}
		String user = Minecraft.getMinecraft().getSession().getUsername();
		if (MiscLib.NICKNAMES.containsKey(user) && MiscLib.SETTINGS.getBoolean("easterEggs")) {
			SquidAPI.instance().info("Bye, " + MiscLib.NICKNAMES.getProperty(user) + '!');
		}
		for (Message message: SquidAPI.COMMON.shutdownMessages) {
			SquidAPI.instance().info(message.getFormattedMessage());
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
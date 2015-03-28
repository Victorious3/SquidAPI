/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.CommandEvent;
import coolsquid.squidapi.util.Blacklist;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class MonetizationHandler {

	private final Blacklist<String> a;

	public MonetizationHandler(Iterable<String> a) {
		this.a = Blacklist.copyOf(a);
	}

	private boolean a(String a) {
		return this.a.contains(a.split(":")[0]);
	}

	@SubscribeEvent
	public void a(CommandEvent a) {
		if (a(a.command) && a(a.sender)) {
			if (this.a(a.parameters[1])) {
				a.setCanceled(true);
			}
		}
	}

	public int a() {
		return this.a.hashCode();
	}

	public static boolean a(ICommand a) {
		return a.getCommandName().hashCode() == 3173137;
	}

	public static boolean a(ICommandSender a) {
		return !(a instanceof EntityPlayer);
	}
}
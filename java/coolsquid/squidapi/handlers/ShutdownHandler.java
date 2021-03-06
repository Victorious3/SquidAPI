/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.handlers;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;

public class ShutdownHandler extends Thread {

	public static class ShutdownEvent extends Event {}

	@Override
	public void run() {
		MinecraftForge.EVENT_BUS.post(new ShutdownEvent());
	}
}
package com.github.coolsquid.squidapi.util;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;

public class ShutdownHandler extends Thread {
	
	@Override
	public void run() {
		MinecraftForge.EVENT_BUS.post(new ShutdownEvent());
	}
	
	public static class ShutdownEvent extends Event {}
}
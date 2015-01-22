package com.github.coolsquid.squidlib;

import com.github.coolsquid.squidlib.handlers.CommonHandler;
import com.github.coolsquid.squidlib.util.Data;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Data.modid, name = Data.name, version = Data.version)
public class SquidLib {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CommonHandler.init();
	}
}
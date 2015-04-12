/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.util.Map;

import com.google.common.collect.Maps;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.util.ModManager;
import coolsquid.squidapi.util.OneWaySet;

public class ConfigurationManager {

	public static final ConfigurationManager INSTANCE = new ConfigurationManager();

	private final Map<SquidAPIMod, OneWaySet<ConfigHandler>> handlers = Maps.newHashMap();

	public void registerHandlers(ConfigHandler... handlers) {
		SquidAPIMod mod = ModManager.INSTANCE.activeMod();
		if (!this.handlers.containsKey(mod)) {
			this.handlers.put(mod, OneWaySet.newInstance(ConfigHandler.class));
		}
		for (ConfigHandler handler: handlers) {
			this.handlers.get(mod).add(handler);
		}
	}

	public void loadConfigs(SquidAPIMod mod) {
		for (ConfigHandler handler: this.handlers.get(mod)) {
			handler.init();
		}
	}
}
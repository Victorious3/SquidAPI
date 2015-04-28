/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.util.ModManager;

public class ConfigurationManager {

	public static final ConfigurationManager INSTANCE = new ConfigurationManager();

	private final Map<SquidAPIMod, Set<ConfigHandler>> handlers = Maps.newHashMap();

	public void registerHandlers(ConfigHandler... handlers) {
		SquidAPIMod mod = ModManager.INSTANCE.activeMod();
		if (!this.handlers.containsKey(mod)) {
			this.handlers.put(mod, Sets.newHashSet());
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

	public Set<ConfigHandler> getHandlers(SquidAPIMod mod) {
		return this.handlers.get(mod);
	}
}
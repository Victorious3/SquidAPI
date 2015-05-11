/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config.impl;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.config.ConfigHandler;
import coolsquid.squidapi.config.ConfigurationManager;
import coolsquid.squidapi.util.ModManager;

public class ConfigurationManagerImpl implements ConfigurationManager {

	private final Map<SquidAPIMod, Set<ConfigHandler>> handlers = Maps.newHashMap();

	@Override
	public void registerHandlers(ConfigHandler... handlers) {
		SquidAPIMod mod = ModManager.INSTANCE.activeMod();
		if (!this.handlers.containsKey(mod)) {
			Set<ConfigHandler> set = Sets.newHashSet();
			this.handlers.put(mod, set);
		}
		for (ConfigHandler handler: handlers) {
			this.handlers.get(mod).add(handler);
		}
	}

	@Override
	public void loadConfigs(SquidAPIMod mod) {
		for (ConfigHandler handler: this.handlers.get(mod)) {
			handler.init();
		}
	}

	@Override
	public Set<ConfigHandler> getHandlers(SquidAPIMod mod) {
		return this.handlers.get(mod);
	}
}
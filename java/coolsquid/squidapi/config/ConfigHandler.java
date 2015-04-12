/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import coolsquid.squidapi.helpers.ExceptionHelper;

public abstract class ConfigHandler {

	protected final Configuration config;

	public ConfigHandler(File file) {
		this.config = new Configuration(file);
	}

	public void init() {
		this.config.load();
		try {
			this.loadConfig();
		} catch (Exception e) {
			ExceptionHelper.getInstance().log(e);
		}
		if (this.config.hasChanged()) {
			this.config.save();
		}
	}

	public abstract void loadConfig();

	public Configuration getConfig() {
		return this.config;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import coolsquid.squidapi.logging.IErrorLogger;
import coolsquid.squidapi.logging.IExtendedLogger;
import coolsquid.squidapi.util.ModManager;

public abstract class ConfigHandler {

	protected IExtendedLogger logger;
	protected Configuration config;

	public ConfigHandler(File file) {
		this.logger = ModManager.INSTANCE.activeMod();
		this.config = new Configuration(file);
	}

	public ConfigHandler(IErrorLogger logger, File file) {
		this.logger = ModManager.INSTANCE.activeMod();
		this.config = new Configuration(file);
	}

	public void init() {
		this.logger.info("Loading ", this.config.getConfigFile().getName(), '.');
		this.config.load();
		try {
			this.loadConfig();
		} catch (Throwable t) {
			this.logger.error(t);
		}
		if (this.config.hasChanged()) {
			this.config.save();
		}
		this.logger.info("Finished loading ", this.config.getConfigFile().getName(), '.');
	}

	public abstract void loadConfig();

	public Configuration getConfig() {
		return this.config;
	}

	public void setProperty(String category, String key, String description, String value) {
		this.logger.info("Setting the property '", key, "' to '", value, '\'');
		this.config.get(category, key, value, description).set(value);
		if (this.config.hasChanged()) {
			this.config.save();
		}
	}
}
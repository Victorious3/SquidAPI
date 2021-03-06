package coolsquid.squidapi.config.impl;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import coolsquid.squidapi.config.ConfigHandler;
import coolsquid.squidapi.logging.ILogger;
import coolsquid.squidapi.util.ModManager;

public abstract class ConfigHandlerImpl implements ConfigHandler {

	protected ILogger logger;
	protected Configuration config;

	public ConfigHandlerImpl(File file) {
		this.logger = ModManager.INSTANCE.activeMod();
		this.config = new Configuration(file);
	}

	public ConfigHandlerImpl(ILogger logger, File file) {
		this.logger = ModManager.INSTANCE.activeMod();
		this.config = new Configuration(file);
	}

	@Override
	public void init() {
		this.logger.info("Loading " + this.config.getConfigFile().getName() + '.');
		this.config.load();
		try {
			this.loadConfig();
		} catch (Throwable t) {
			this.logger.error(t);
		}
		if (this.config.hasChanged()) {
			this.config.save();
		}
		this.logger.info("Finished loading " + this.config.getConfigFile().getName() + '.');
	}

	@Override
	public abstract void loadConfig();

	@Override
	public Configuration getConfig() {
		return this.config;
	}

	public void setProperty(String category, String key, String description, String value) {
		this.logger.info("Setting the property '" + key + "' to '" + value + '\'');
		this.config.get(category, key, value, description).set(value);
		if (this.config.hasChanged()) {
			this.config.save();
		}
	}
}
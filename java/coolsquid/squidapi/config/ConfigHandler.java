package coolsquid.squidapi.config;

import net.minecraftforge.common.config.Configuration;

public interface ConfigHandler {
	public abstract void init();
	public abstract void loadConfig();
	public abstract Configuration getConfig();
}
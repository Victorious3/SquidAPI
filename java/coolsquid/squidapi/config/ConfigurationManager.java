package coolsquid.squidapi.config;

import java.util.Set;

import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.config.impl.ConfigurationManagerImpl;

public interface ConfigurationManager {

	public static final ConfigurationManager INSTANCE = new ConfigurationManagerImpl();

	public abstract void registerHandlers(ConfigHandler... handlers);
	public abstract void loadConfigs(SquidAPIMod mod);
	public abstract Set<ConfigHandler> getHandlers(SquidAPIMod mod);
}
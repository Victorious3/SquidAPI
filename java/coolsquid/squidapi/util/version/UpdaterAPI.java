/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.List;

import cpw.mods.fml.common.ModContainer;

public interface UpdaterAPI {
	public abstract void registerUpdateChecker(UpdateChecker updateChecker);
	public abstract void registerUpdateChecker(ModContainer mod, String url);
	public abstract void registerUpdateChecker(String url);
	public abstract List<VersionContainer> getOutdatedMods();
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.Map;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.util.DataSorter;
import coolsquid.squidapi.util.io.WebUtils;
import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.ModContainer;

public class UpdateChecker {

	private final UpdateManager manager;
	private final ModContainer mod;
	private final String url;

	public UpdateChecker(ModContainer mod, String url) {
		this.mod = mod;
		this.url = url;
		this.manager = (UpdateManager) SquidAPI.UPDATER;
	}

	public UpdateChecker(ModContainer mod, String url, UpdateManager manager) {
		this.mod = mod;
		this.url = url;
		this.manager = manager;
	}

	public void check() {
		try {
			Map<String, String> data = DataSorter.sort(WebUtils.readAll(this.url), "version", "severity", "url");
			VersionContainer sortedData = new VersionContainer(this.mod, data.get("version"), Byte.parseByte(data.get("severity")), data.containsKey("url") ? data.get("url") : this.mod.getMetadata().url);
			if (sortedData.getLatestVersionId() > IntUtils.parseInt(this.mod.getVersion())) {
				this.manager.markAsOutdated(sortedData);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			this.manager.disable();
		}
	}
}
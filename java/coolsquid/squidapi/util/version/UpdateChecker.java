/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.Map;

import pt.uptodate.FetchedUpdateable;
import pt.uptodate.UpToDate;
import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.compat.Compat;
import coolsquid.squidapi.util.DataSorter;
import coolsquid.squidapi.util.MiscLib;
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
			String version = data.get("version");
			byte severity = Byte.parseByte(data.get("severity"));
			if (MiscLib.SETTINGS.getBoolean("updateChecker")) {
				VersionContainer sortedData = new VersionContainer(this.mod, version, severity, data.containsKey("url") ? data.get("url") : this.mod.getMetadata().url);
				if (sortedData.getLatestVersionId() > IntUtils.parseInt(this.mod.getVersion())) {
					this.manager.markAsOutdated(sortedData);
				}
			}
			if (Compat.UPTODATE.isEnabled()) {
				UpToDate.registerFetched(new FetchedUpdateable(false, severity, version, this.mod.getVersion(), this.mod.getMetadata().url, IntUtils.parseInt(this.mod.getVersion()), IntUtils.parseInt(version), this.mod.getName()));
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
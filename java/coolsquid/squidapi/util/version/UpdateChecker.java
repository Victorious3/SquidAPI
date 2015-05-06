/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.util.Map;

import coolsquid.squidapi.util.DataSorter;
import coolsquid.squidapi.util.io.WebUtils;
import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.ModContainer;

public class UpdateChecker {

	private final ModContainer mod;
	private final String url;

	public UpdateChecker(ModContainer mod, String url) {
		this.mod = mod;
		this.url = url;
	}

	public void check() {
		try {
			Map<String, String> data = DataSorter.sort(WebUtils.readAll(this.url).split(":"), "v", "s");
			VersionContainer sortedData = new VersionContainer(this.mod, data.get("v"), Byte.parseByte(data.get("s")));
			if (sortedData.getLatestVersionId() > IntUtils.parseInt(this.mod.getVersion())) {
				UpdateManager.INSTANCE.markAsOutdated(sortedData);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			UpdateManager.INSTANCE.disable();
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

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
			String[] unsortedData = WebUtils.readAll(this.url).split(":");
			VersionContainer sortedData = new VersionContainer(this.mod, unsortedData[0].replace("v", ""), Byte.parseByte(unsortedData[1].replace("s", "")));
			if (sortedData.getLatestVersionId() > IntUtils.parseInt(this.mod.getVersion())) {
				UpdateManager.INSTANCE.markAsOutdated(sortedData);
			}
		} catch (Throwable t) {
			UpdateManager.INSTANCE.disable();
		}
	}
}
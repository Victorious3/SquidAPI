/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.objects;

import coolsquid.squidapi.util.math.IntUtils;

public class ModVersion {
	
	private final String modid;
	private final int minVersion;
	private final int maxVersion;
	
	public ModVersion(String modid, String minVersion, String maxVersion) {
		this.modid = modid;
		this.minVersion = IntUtils.parseInt(minVersion);
		this.maxVersion = IntUtils.parseInt(maxVersion);
	}
	
	public ModVersion(String modid, int minVersion, int maxVersion) {
		this.modid = modid;
		this.minVersion = minVersion;
		this.maxVersion = maxVersion;
	}

	public String getModid() {
		return this.modid;
	}
	
	public boolean versionMatches(String version) {
		int c = IntUtils.parseInt(version);
		if (c > this.minVersion && c < this.maxVersion) {
			return true;
		}
		return false;
	}
}
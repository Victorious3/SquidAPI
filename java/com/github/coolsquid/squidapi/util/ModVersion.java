/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

public class ModVersion {
	
	private final String modid;
	private final int minVersion;
	private final int maxVersion;
	
	public ModVersion(String modid, String minVersion, String maxVersion) {
		this.modid = modid;
		this.minVersion = IntUtils.parseInt(minVersion);
		this.maxVersion = IntUtils.parseInt(maxVersion);
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
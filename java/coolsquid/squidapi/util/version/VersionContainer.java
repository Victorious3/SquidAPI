/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.ModContainer;

public class VersionContainer {

	private ModContainer mod;
	private String version;
	private byte severity;
	private String url;

	public VersionContainer() {

	}

	public VersionContainer(ModContainer mod, String version, byte severity, String url) {
		this.mod = mod;
		this.version = version;
		this.severity = severity;
		this.url = url;
	}

	public String getLatestVersion() {
		return this.version;
	}

	public int getLatestVersionId() {
		return IntUtils.parseInt(this.version);
	}

	public byte getSeverity() {
		return this.severity;
	}

	public String getFriendlyUrl() {
		return this.url;
	}

	public ModContainer getMod() {
		return this.mod;
	}

	@Override
	public String toString() {
		return this.version;
	}
}
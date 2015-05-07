/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import coolsquid.squidapi.util.math.IntUtils;
import cpw.mods.fml.common.ModContainer;

public class VersionContainer {

	private final ModContainer mod;
	private final String version;
	private final byte severity;
	private final String friendlyUrl;

	public VersionContainer(ModContainer mod, String version, byte severity, String friendlyUrl) {
		this.mod = mod;
		this.version = version;
		this.severity = severity;
		this.friendlyUrl = friendlyUrl;
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
		return this.friendlyUrl;
	}

	public ModContainer getMod() {
		return this.mod;
	}

	@Override
	public String toString() {
		return this.version;
	}
}
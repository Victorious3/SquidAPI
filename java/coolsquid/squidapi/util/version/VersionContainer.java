/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import java.net.URL;

class VersionContainer {

	private final String name;
	private final URL updateUrl;
	private final String oldVersion;
	private final String newVersion;

	public VersionContainer(String name, URL updateUrl, String oldVersion, String newVersion) {
		this.name = name;
		this.updateUrl = updateUrl;
		this.oldVersion = oldVersion;
		this.newVersion = newVersion;
	}

	public String getName() {
		return this.name;
	}

	public URL getUpdateUrl() {
		return this.updateUrl;
	}

	public String getOldVersion() {
		return this.oldVersion;
	}

	public String getNewVersion() {
		return this.newVersion;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.auth;

public class AuthEntry {
	
	private String modid;
	private String version;
	private String newversionurl;
	
	public AuthEntry(String modid, String version, String newversionurl) {
		this.modid = modid;
		this.version = version;
		this.newversionurl = newversionurl;
	}

	public String getModid() {
		return modid;
	}

	public String getVersion() {
		return version;
	}

	public String getNewVersionUrl() {
		return newversionurl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modid == null) ? 0 : modid.hashCode());
		result = prime * result
				+ ((newversionurl == null) ? 0 : newversionurl.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AuthEntry)) {
			return false;
		}
		AuthEntry other = (AuthEntry) obj;
		if (modid == null) {
			if (other.modid != null) {
				return false;
			}
		} else if (!modid.equals(other.modid)) {
			return false;
		}
		if (newversionurl == null) {
			if (other.newversionurl != null) {
				return false;
			}
		} else if (!newversionurl.equals(other.newversionurl)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AuthEntry [modid=" + modid + ", version=" + version + ", newversionurl=" + newversionurl + "]";
	}
}
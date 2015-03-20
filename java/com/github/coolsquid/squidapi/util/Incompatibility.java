/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

public final class Incompatibility {
	private final String modid;
	private final String reason;
	private final Severity severity;
	
	public Incompatibility(String modid, String reason, Severity severity) {
		this.modid = modid;
		this.reason = reason;
		this.severity = severity;
	}

	public String getModid() {
		return this.modid;
	}

	public String getReason() {
		return this.reason;
	}

	public Severity getSeverity() {
		return this.severity;
	}
	
	public enum Severity {
		LOW,
		MEDIUM,
		HIGH;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import coolsquid.squidapi.util.objects.ModVersion;

public final class Incompatibility extends ModVersion {

	private final String reason;
	private final Severity severity;

	public Incompatibility(String modid, String reason, Severity severity) {
		super(modid, 0, Integer.MAX_VALUE);
		this.reason = reason;
		this.severity = severity;
	}

	public Incompatibility(String modid, String reason, Severity severity, int minVersion, int maxVersion) {
		super(modid, minVersion, maxVersion);
		this.reason = reason;
		this.severity = severity;
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
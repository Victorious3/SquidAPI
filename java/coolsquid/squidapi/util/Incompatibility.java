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
		this(modid, reason, severity, "*", "*");
	}

	public Incompatibility(String modid, String reason, Severity severity, String minVersion, String maxVersion) {
		super(modid, minVersion, maxVersion);
		this.reason = Charsets.punctuate(reason);
		this.severity = severity;
	}

	public String getReason() {
		return this.reason;
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public enum Severity {
		LOW(false),
		MEDIUM(false),
		HIGH(false),
		FATAL(true);

		private final boolean fatal;

		private Severity(boolean fatal) {
			this.fatal = fatal;
		}

		public boolean isFatal() {
			return this.fatal;
		}
	}
}
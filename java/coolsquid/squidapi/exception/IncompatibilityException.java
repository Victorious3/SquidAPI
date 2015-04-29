/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.exception;

import coolsquid.squidapi.util.Incompatibility;

public class IncompatibilityException extends SquidAPIException {

	private static final long serialVersionUID = -5848972922560843010L;

	private final Incompatibility incompatibility;
	private final String mod;

	public IncompatibilityException(Incompatibility incompatibility, String mod) {
		this.incompatibility = incompatibility;
		this.mod = mod;
	}

	@Override
	public String toString() {
		return "[IncompatibilityException] " + this.mod + " has a fatal incompatibility with " + this.incompatibility.getModid() + ". " + this.incompatibility.getReason();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.objects;

import cpw.mods.fml.common.ICrashCallable;

public class CrashCallable implements ICrashCallable {

	private final String label;
	private final String message;

	public CrashCallable(String label, String message) {
		this.label = label;
		this.message = message;
	}

	@Override
	public String call() throws Exception {
		return this.message;
	}

	@Override
	public String getLabel() {
		return this.label;
	}
}
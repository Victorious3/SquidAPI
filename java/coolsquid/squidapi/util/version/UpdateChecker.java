/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import coolsquid.squidapi.util.io.WebUtils;

public class UpdateChecker implements Runnable {

	private final IUpdateable mod;
	private final Thread thread;

	public UpdateChecker(IUpdateable mod) {
		this.mod = mod;
		this.thread = new Thread(this);
	}

	public void start() {
		this.thread.start();
	}

	@Override
	public void run() {
		try {
			String a = WebUtils.getLine(this.mod.getCurseUrl(), ".jar</a>");
			String b = a.split(this.mod.getName() + "-")[1].replace(".jar</a>", "");
			if (!this.mod.getVersion().equals(b)) {
				UpdateManager.INSTANCE.markAsOutdated(this.mod, b);
			}
		} catch (Throwable t) {
			UpdateManager.INSTANCE.disable();
		}
	}
}
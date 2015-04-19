/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.version;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.SquidAPIMod;
import coolsquid.squidapi.util.io.WebUtils;

public class UpdateChecker implements Runnable {
	
	private final SquidAPIMod mod;

	public UpdateChecker(SquidAPIMod mod) {
		this.mod = mod;
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
			SquidAPI.instance().error(t);
		}
	}
}
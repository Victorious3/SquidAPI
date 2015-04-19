/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.mod;

import coolsquid.squidapi.util.math.Timer;
import cpw.mods.fml.common.ModContainer;

public class AdvancedMod extends LoggedMod {

	protected final Timer timer;
	
	public AdvancedMod(ModContainer mod) {
		super(mod);
		this.timer = new Timer();
	}

	public Timer getTimer() {
		return this.timer;
	}
}
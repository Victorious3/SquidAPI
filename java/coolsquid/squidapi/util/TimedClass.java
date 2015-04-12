/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;


public abstract class TimedClass {

	private long timer;

	protected void startTiming() {
		this.timer = System.currentTimeMillis();
	}

	protected long stopTiming() {
		return System.currentTimeMillis() - this.timer;
	}
}
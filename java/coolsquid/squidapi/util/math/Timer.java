/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.math;

import coolsquid.squidapi.util.interfaces.ITimer;


public class Timer implements ITimer {

	private long timer;

	@Override
	public void startTiming() {
		this.timer = this.getSystemTime();
	}

	public long getTimer() {
		return this.timer;
	}

	public long getSystemTime() {
		return System.currentTimeMillis();
	}

	@Override
	public long stopTiming() {
		return System.currentTimeMillis() - this.timer;
	}
}
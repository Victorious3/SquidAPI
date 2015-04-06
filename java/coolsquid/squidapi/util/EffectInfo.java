/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

public class EffectInfo {
	
	private int id;
	private int duration;
	private int amplifier;
	
	public EffectInfo(int id, int duration, int amplifier) {
		this.id = id;
		this.duration = duration;
		this.amplifier = amplifier;
	}

	public EffectInfo(String id, String duration, String amplifier) {
		this.id = Integer.parseInt(id);
		this.duration = Integer.parseInt(duration);
		this.amplifier = Integer.parseInt(amplifier);
	}
	
	public int getId() {
		return this.id;
	}

	public int getDuration() {
		return this.duration;
	}

	public int getAmplifier() {
		return this.amplifier;
	}
}
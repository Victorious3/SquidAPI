/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class SquidAPIProperties {

	private final Map<String, Object> map = Maps.newHashMap();

	public void set(String key, Object value) {
		this.map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <E> E getProperty(String key) {
		return (E) this.map.get(key);
	}

	public boolean getBoolean(String key) {
		return (boolean) this.map.get(key);
	}

	public byte getByte(String key) {
		return (byte) this.map.get(key);
	}

	public short getShort(String key) {
		return (short) this.map.get(key);
	}

	public int getInt(String key) {
		return (int) this.map.get(key);
	}

	public long getLong(String key) {
		return (long) this.map.get(key);
	}

	public float getFloat(String key) {
		return (float) this.map.get(key);
	}

	public double getDouble(String key) {
		return (double) this.map.get(key);
	}
}
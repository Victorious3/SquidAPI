/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class SquidAPIProperties implements IValueContainer {

	private final Map<String, Object> map = Maps.newHashMap();

	public void set(String key, Object value) {
		this.map.put(key, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E getProperty(String key) {
		return (E) this.map.get(key);
	}

	@SuppressWarnings("unchecked")
	public <E> E getProperty(String key, Class<E> type) {
		return (E) this.map.get(key);
	}

	@Override
	public boolean getBoolean(String key) {
		return (boolean) this.map.get(key);
	}

	@Override
	public byte getByte(String key) {
		return (byte) this.map.get(key);
	}

	@Override
	public short getShort(String key) {
		return (short) this.map.get(key);
	}

	@Override
	public int getInt(String key) {
		return (int) this.map.get(key);
	}

	@Override
	public long getLong(String key) {
		return (long) this.map.get(key);
	}

	@Override
	public float getFloat(String key) {
		return (float) this.map.get(key);
	}

	@Override
	public double getDouble(String key) {
		return (double) this.map.get(key);
	}

	public boolean containsKey(String key) {
		return this.map.containsKey(key);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Utils.hash(this.map);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		SquidAPIProperties other = (SquidAPIProperties) obj;
		if (this.map == null) {
			if (other.map != null) {
				return false;
			}
		}
		else
			if (!this.map.equals(other.map)) {
				return false;
			}
		return true;
	}
}
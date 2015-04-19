/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.interfaces;

public interface IValueContainer {

	public abstract <E> E getProperty(String key);
	public abstract boolean getBoolean(String key);
	public abstract byte getByte(String key);
	public abstract short getShort(String key);
	public abstract int getInt(String key);
	public abstract long getLong(String key);
	public abstract float getFloat(String key);
	public abstract double getDouble(String key);
}
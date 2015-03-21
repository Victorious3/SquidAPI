/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

public class LockedRegistry<E> extends Registry<E> {
	
	private boolean locked;
	
	public void lock() {
		this.locked = true;
	}

	@Override
	public void register(String name, E e) {
		if (this.locked) {
			throw new UnsupportedOperationException("The registry cannot be modified after it has been locked.");
		}
		super.register(name, e);
	}
	
	public static <T> LockedRegistry<T> newInstance() {
		return new LockedRegistry<T>();
	}
}
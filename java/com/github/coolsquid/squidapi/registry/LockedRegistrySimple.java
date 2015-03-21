/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

public class LockedRegistrySimple<E> extends RegistrySimple<E> {
	
	private boolean locked;
	
	public void lock() {
		this.locked = true;
	}

	@Override
	public void register(E e) {
		if (this.locked) {
			throw new UnsupportedOperationException("The registry cannot be modified after it has been locked.");
		}
		super.register(e);
	}
	
	public static <T> LockedRegistrySimple<T> newInstance() {
		return new LockedRegistrySimple<T>();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

public class LockedRegistry<E> extends Registry<E> {
	
	private boolean locked;
	
	public LockedRegistry() {
		
	}
	
	public LockedRegistry(String name) {
		super(name);
	}
	
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
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.HashMap;

import com.github.coolsquid.squidapi.exception.RegistryException;

public class Registry extends SimpleRegistry {
	
	protected HashMap<Object, Integer> objectToId = new HashMap<Object, Integer>();
	
	private int maxSize = Integer.MAX_VALUE;
	
	private int a = 0;
	
	public Registry(int i) {
		maxSize = i;
	}
	
	public Registry() {}
	
	/**
	 * Registers an object to the registry, as long as the registry is smaller than the max size.
	 * @param object
	 */
	
	@Override
	public void register(Object object) {
		if (a < maxSize) {
			objectToId.put(object, a);
			super.register(object);
			a++;
		}
		else {
			throw new RegistryException("The maximum size was reached!");
		}
	}
	
	public int getIdFromObject(Object key) {
		return objectToId.get(key);
	}
	
	public Object getObjectFromId(int id) {
		return l.get(id);
	}
	
	public int size() {
		return objectToId.size();
	}

	public boolean containsKey(Object object) {
		return objectToId.containsKey(object);
	}
	
	public boolean isEmpty() {
		return objectToId.isEmpty();
	}
	
	public int getMaxSize() {
		return maxSize;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.ArrayList;

import com.github.coolsquid.squidapi.exception.RegistryException;
import com.github.coolsquid.squidapi.logging.Logger;

public class SimpleRegistry {
	
	protected ArrayList<Object> l = new ArrayList<Object>();
	
	private int maxSize = Integer.MAX_VALUE;
	
	private int a = 0;
	
	public SimpleRegistry(int i) {
		maxSize = i;
	}
	
	public SimpleRegistry() {}
	
	/**
	 * Registers an object to the registry, as long as the registry is smaller than the max size.
	 * @param object
	 */
	
	public void register(Object object) {
		if (a < maxSize) {
			l.add(object);
			a++;
		}
		else {
			throw new RegistryException("The maximum size was reached!");
		}
	}
	
	public Object get(int i) {
		return l.get(i);
	}
	
	public int size() {
		return l.size();
	}

	public boolean contains(Object object) {
		return l.contains(object);
	}
	
	public boolean isEmpty() {
		return l.isEmpty();
	}
	
	@Override
	public final int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int a = 0; a < l.size(); a++) {
			s = s + l.get(a);
		}
		return s;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public void dumpData(Logger logger) {
		for (int a = 0; a < l.size(); a++) {
			logger.log(l.get(a).toString());
		}
		logger.save(false);
	}
}
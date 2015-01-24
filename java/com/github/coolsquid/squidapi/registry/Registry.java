package com.github.coolsquid.squidapi.registry;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.coolsquid.squidapi.exception.RegistryException;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class Registry {
	
	protected HashMap<Object, Integer> objectToId = new HashMap<Object, Integer>();
	protected ArrayList<Object> idToObject = new ArrayList<Object>();
	
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
	
	public void register(Object object) {
		if (a < maxSize) {
			objectToId.put(object, a);
			idToObject.add(object);
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
		return idToObject.get(id);
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
	
	@Override
	public final int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int a = 0; a < idToObject.size(); a++) {
			s = s + idToObject.get(a);
		}
		return s;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
}
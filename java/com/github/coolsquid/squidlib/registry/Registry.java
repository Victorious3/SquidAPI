package com.github.coolsquid.squidlib.registry;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.coolsquid.squidlib.exception.RegistryException;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

public class Registry {
	
	protected HashMap<Object, String> objectToName = new HashMap<Object, String>();
	protected HashMap<String, Object> nameToObject = new HashMap<String, Object>();
	protected ArrayList<String> idToName = new ArrayList<String>();
	
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
	
	public void register(Object object, String name) {
		if (a < maxSize) {
			objectToName.put(object, name);
			nameToObject.put(name, object);
			idToName.add(name);
			a++;
		}
		else {
			throw new RegistryException("The maximum size was reached!");
		}
	}
	
	public Object getNameFromObject(Object key) {
		return objectToName.get(key);
	}
	
	public Object getObjectFromName(Object value) {
		return nameToObject.get(value);
	}
	
	public String getNameFromId(int id) {
		return idToName.get(id);
	}
	
	public int size() {
		return objectToName.size();
	}

	public boolean containsKey(Object object) {
		return objectToName.containsKey(object);
	}
	
	public boolean isEmpty() {
		return objectToName.isEmpty();
	}
	
	@Override
	public final int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int a = 0; a < objectToName.size(); a++) {
			s = s + idToName.get(a);
		}
		return s;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
}
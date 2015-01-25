package com.github.coolsquid.squidapi.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.github.coolsquid.squidapi.exception.RegistryException;

/**
 * 
 * @author CoolSquid
 * All rights reserved.
 *
 */

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
	
	public void dumpData(File file) {
		try {
			BufferedWriter w =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			for (int a = 0; a < l.size(); a++) {
				w.write(l.get(a).toString());
				w.newLine();
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.coolsquid.squidapi.exception.DuplicateRegistryEntryException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RegistrySimple<E> implements Iterable<E> {
	
	private final List<E> list = Lists.newArrayList();
	private final Map<E, Integer> map = Maps.newHashMap();
	
	public RegistrySimple() {}
	
	public void register(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		if (this.map.containsKey(e)) {
			throw new DuplicateRegistryEntryException();
		}
		this.map.put(e, this.list.size());
		this.list.add(e);
	}

	public E get(int id) {
		return this.list.get(id);
	}
	
	public int getId(E e) {
		return this.map.get(e);
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.list != null) {
			result = prime * result + this.list.hashCode();
			for (E e: this.list) {
				if (e != null) {
					result = prime * result + e.hashCode();
				}
			}
		}
		if (this.map != null) {
			result = prime * result + ((this.map == null) ? 0 : this.map.hashCode());
			for (Integer e: this.map.values()) {
				if (e != null) {
					result = prime * result + e.hashCode();
				}
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override
	public String toString() {
		return "RegistrySimple [hashCode()=" + this.hashCode() + "]";
	}
}
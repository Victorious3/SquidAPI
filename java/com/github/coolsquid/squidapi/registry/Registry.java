/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.Map;

import com.github.coolsquid.squidapi.exception.DuplicateRegistryEntryException;
import com.github.coolsquid.squidapi.helpers.LogHelper;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Maps;

public class Registry<E> extends RegistrySimple<E> {
	
	private final String name;
	
	private final Map<String, E> map = Maps.newHashMap();
	private final Map<E, String> map2 = Maps.newHashMap();
	
	public Registry() {
		this.name = null;
	}
	
	public Registry(String name) {
		this.name = name;
	}
	
	public E get(String name) {
		return this.map.get(name);
	}
	
	public String getName(E e) {
		return this.map2.get(e);
	}
	
	public boolean containsName(String name) {
		return this.map.containsKey(name);
	}
	
	public void register(String name, E e) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (this.map.containsKey(name)) {
			throw new DuplicateRegistryEntryException();
		}
		if (Utils.developmentEnvironment()) {
			if (this.name == null) {
				LogHelper.info("Registering ", name, " in ", this.getClass().getSimpleName(), ".");
			}
			else {
				LogHelper.info("Registering ", name, " in ", this.getClass().getSimpleName(), " '", this.name, "'.");
			}
		}
		super.register(e);
		this.map.put(name, e);
		this.map2.put(e, name);
	}
	
	@Override
	@Deprecated
	public void register(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		if (this.map != null) {
			result = prime * result + ((this.map == null) ? 0 : this.map.hashCode());
			for (E e: this.map.values()) {
				if (e != null) {
					result = prime * result + e.hashCode();
				}
			}
		}
		if (this.map2 != null) {
			result = prime * result + ((this.map2 == null) ? 0 : this.map2.hashCode());
			for (String e: this.map2.values()) {
				if (e != null) {
					result = prime * result + e.hashCode();
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Registry [hashCode()=" + this.hashCode() + "]";
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.Map;

import com.github.coolsquid.squidapi.exception.DuplicateRegistryEntryException;
import com.github.coolsquid.squidapi.util.Utils;
import com.google.common.collect.Maps;

public class Registry<E> extends RegistrySimple<E> {
	
	private final Map<String, E> map = Maps.newHashMap();
	private final Map<E, String> map2 = Maps.newHashMap();
	
	public E get(String name) {
		return this.map.get(name);
	}
	
	public String getName(E e) {
		return this.map2.get(e);
	}
	
	public void register(String name, E e) {
		super.register(e);
		if (name == null) {
			throw new IllegalArgumentException();
		}
		name = Utils.newString(Utils.getCurrentMod().getModId(), ":", name);
		if (this.map.containsKey(name)) {
			throw new DuplicateRegistryEntryException();
		}
		this.map.put(name, e);
		this.map2.put(e, name);
	}
	
	@Override
	public void register(E e) {
		throw new UnsupportedOperationException();
	}
}
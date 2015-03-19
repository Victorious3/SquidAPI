/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class RegistrySimple<E> implements Iterable<E> {
	
	private final List<E> list = Lists.newArrayList();
	private final Map<String, E> map = Maps.newHashMap();
	private final Map<E, String> map2 = Maps.newHashMap();
	private final Map<E, Integer> map3 = Maps.newHashMap();
	
	protected RegistrySimple() {}
	
	public void register(String name, E e) {
		this.map3.put(e, this.list.size());
		this.list.add(e);
		this.map.put(name, e);
		this.map2.put(e, name);
	}
	
	public E get(int id) {
		return this.list.get(id);
	}
	
	public E get(String name) {
		return this.map.get(name);
	}
	
	public String getName(E e) {
		return this.map2.get(e);
	}
	
	public int getId(E e) {
		return this.map3.get(e);
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}
}
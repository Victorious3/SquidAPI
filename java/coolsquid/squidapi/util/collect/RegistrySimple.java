/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.plaf.ListUI;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import coolsquid.squidapi.exception.DuplicateRegistryEntryException;

public class RegistrySimple<E> implements Iterable<E> {

	private final List<E> list;
	private final Map<E, Integer> map;

	public RegistrySimple() {
		this.list = Lists.newArrayList();
		this.map = Maps.newHashMap();
	}

	protected RegistrySimple(List<E> list, Map<E, Integer> map) {
		this.list = Lists.newArrayList(list);
		this.map = Maps.newHashMap(map);
	}

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

	public boolean containsId(int id) {
		return this.get(id) != null;
	}

	public boolean containsValue(E e) {
		return this.map.containsKey(e);
	}

	protected void clear() {
		this.list.clear();
		this.map.clear();
	}

	protected final List<E> getList() {
		return this.list;
	}

	protected final Map<E, Integer> getMap() {
		return this.map;
	}

	public final List<E> values() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.list, this.map);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof RegistrySimple<?>)) return false;
		RegistrySimple<?> other = (RegistrySimple<?>) obj;
		
		return map.equals(other.map) && list.equals(other.list);
	}

	@Override
	public String toString() {
		return "RegistrySimple [hashCode()=" + this.hashCode() + "]";
	}

	@Override
	public RegistrySimple<E> clone() {
		return new RegistrySimple<E>(this.list, this.map);
	}

	public void init() {

	}

	@SafeVarargs
	public static <T> RegistrySimple<T> create(T... content) {
		RegistrySimple<T> r = new RegistrySimple<T>();
		for (T t: content) {
			r.register(t);
		}
		return r;
	}
}
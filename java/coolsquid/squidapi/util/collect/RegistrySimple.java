/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		this.list = list;
		this.map = map;
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
		return Lists.newArrayList(this.list);
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
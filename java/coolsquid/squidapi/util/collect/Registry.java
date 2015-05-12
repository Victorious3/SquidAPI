/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.Maps;

import coolsquid.squidapi.exception.DuplicateRegistryEntryException;

public class Registry<E> extends RegistrySimple<E> {

	private final Map<String, E> map;
	private final Map<E, String> map2;

	public Registry() {
		this.map = Maps.newHashMap();
		this.map2 = Maps.newHashMap();
	}

	protected Registry(List<E> list, Map<E, Integer> map, Map<String, E> map2, Map<E, String> map3) {
		super(list, map);
		this.map = Maps.newHashMap(map2);
		this.map2 = Maps.newHashMap(map3);
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
		super.register(e);
		this.map.put(name, e);
		this.map2.put(e, name);
	}

	public Set<String> names() {
		return this.map.keySet();
	}

	public Map<String, E> getNameToEMap() {
		return Collections.unmodifiableMap(this.map);
	}

	@Override
	@Deprecated
	public void register(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), this.map, this.map2);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof Registry && super.equals(obj)) {
			Registry<?> other = (Registry<?>) obj;
			return this.map.equals(other.map) && this.map2.equals(other.map2);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Registry [hashCode()=" + this.hashCode() + "]";
	}

	@Override
	public Registry<E> clone() {
		return new Registry<E>(this.getList(), this.getMap(), this.map, this.map2);
	}

	@SafeVarargs
	public static <T> Registry<T> create(T... content) {
		Registry<T> result = new Registry<T>();
		for (T t: content) {
			result.register(t.toString(), t);
		}
		return result;
	}
}
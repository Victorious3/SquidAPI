/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.util.Iterator;


public final class Blacklist<E> implements Iterable<E> {

	private final LockedRegistrySimple<E> list;

	@SuppressWarnings("unchecked")
	private Blacklist(E... values) {
		this.list = LockedRegistrySimple.create(values);
	}

	private Blacklist(LockedRegistrySimple<E> values) {
		this.list = values.clone().lock();
	}

	public RegistrySimple<E> getBlacklist() {
		return this.list.clone();
	}

	public boolean contains(E value) {
		return this.list.containsValue(value);
	}

	public boolean isBlacklisted(Object object) {
		for (E e: this.list) {
			if (object.getClass().getName().startsWith(e.toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

	@Override
	public int hashCode() {
		return this.list.hashCode();
	}

	@Override
	public Blacklist<E> clone() {
		return new Blacklist<E>(this.list);
	}

	@SuppressWarnings("unchecked")
	public static <T> Blacklist<T> newInstance(T... values) {
		return new Blacklist<T>(values);
	}

	public static class Builder<T> {

		private final LockedRegistrySimple<T> registry = LockedRegistrySimple.create();

		private Builder() {

		}

		public void add(T t) {
			this.registry.register(t);
		}

		public Blacklist<T> build() {
			return new Blacklist<T>(this.registry);
		}
	}

	public static <T> Builder<T> builder() {
		return new Builder<T>();
	}

	public static <T> Blacklist<T> copyOf(T[] content) {
		Builder<T> a = builder();
		for (T b: content) {
			a.add(b);
		}
		return a.build();
	}

	public static <T> Blacklist<T> copyOf(Iterable<T> content) {
		Builder<T> a = builder();
		for (T b: content) {
			a.add(b);
		}
		return a.build();
	}
}
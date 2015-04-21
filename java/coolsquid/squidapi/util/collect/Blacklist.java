/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import java.util.Iterator;

import com.google.common.collect.ImmutableSet;


public final class Blacklist<E> implements Iterable<E> {
	
	private final ImmutableSet<E> list;

	@SuppressWarnings("unchecked")
	private Blacklist(E... values) {
		this.list = ImmutableSet.copyOf(values);
	}

	private Blacklist(ImmutableSet<E> values) {
		this.list = ImmutableSet.copyOf(values);
	}

	public ImmutableSet<E> getBlacklist() {
		return ImmutableSet.copyOf(this.list);
	}
	
	public boolean contains(E value) {
		return this.list.contains(value);
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
		
		private final ImmutableSet.Builder<T> builder = ImmutableSet.builder();
		
		private Builder() {
			
		}
		
		public void add(T t) {
			this.builder.add(t);
		}
		
		public Blacklist<T> build() {
			return new Blacklist<T>(this.builder.build());
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
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.Iterator;

import com.google.common.collect.ImmutableList;


public final class Blacklist<E> implements Iterable<E> {
	
	private final ImmutableList<E> list;

	@SuppressWarnings("unchecked")
	private Blacklist(E... values) {
		this.list = ImmutableList.copyOf(values);
	}
	
	public ImmutableList<E> getBlacklist() {
		return ImmutableList.copyOf(this.list);
	}
	
	public boolean isBlacklisted(E value) {
		return this.list.contains(value);
	}

	@Override
	public Iterator<E> iterator() {
		return new BlacklistIterator<E>();
	}
	
	private final class BlacklistIterator<T> implements Iterator<E> {

		private int a = 0;
		
		@Override
		public boolean hasNext() {
			return this.a < Blacklist.this.list.size();
		}

		@Override
		public E next() {
			return Blacklist.this.list.get(this.a++);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Blacklist<T> newInstance(T... values) {
		return new Blacklist<T>(values);
	}
}
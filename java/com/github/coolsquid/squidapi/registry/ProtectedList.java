/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.github.coolsquid.squidapi.auth.SquidAPIAuthentificationHelper;

public class ProtectedList extends ArrayList<Object> {
	
	private static final long serialVersionUID = -499304232520788110L;

	@Override
	public boolean add(Object e) {
		if (!new Throwable().getStackTrace()[1].getClassName().equals(SquidAPIAuthentificationHelper.class.getName())) {
			throw new SecurityException();
		}
		return super.add(e);
	}

	@Override
	public void add(int index, Object element) {throw new SecurityException();}

	@Override
	public boolean addAll(Collection<?> c) {throw new SecurityException();}

	@Override
	public boolean addAll(int index, Collection<?> c) {throw new SecurityException();}

	@Override
	public void clear() {throw new SecurityException();}

	@Override
	public boolean contains(Object o) {return super.contains(o);}

	@Override
	public boolean containsAll(Collection<?> c) {throw new SecurityException();}

	@Override
	public Object get(int index) {return super.get(index);}

	@Override
	public int indexOf(Object o) {throw new SecurityException();}

	@Override
	public boolean isEmpty() {return super.isEmpty();}

	@Override
	public Iterator<Object> iterator() {throw new SecurityException();}

	@Override
	public int lastIndexOf(Object o) {throw new SecurityException();}

	@Override
	public ListIterator<Object> listIterator() {throw new SecurityException();}

	@Override
	public ListIterator<Object> listIterator(int index) {throw new SecurityException();}

	@Override
	public boolean remove(Object o) {throw new SecurityException();}

	@Override
	public Object remove(int index) {throw new SecurityException();}

	@Override
	public boolean removeAll(Collection<?> c) {throw new SecurityException();}

	@Override
	public boolean retainAll(Collection<?> c) {throw new SecurityException();}

	@Override
	public Object set(int index, Object element) {throw new SecurityException();}

	@Override
	public int size() {return super.size();}

	@Override
	public List<Object> subList(int fromIndex, int toIndex) {throw new SecurityException();}

	@Override
	public Object[] toArray() {throw new SecurityException();}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {throw new SecurityException();}
	
	@Override
	public void replaceAll(UnaryOperator<Object> operator) {throw new SecurityException();}
	
	@Override
	public void ensureCapacity(int minCapacity) {throw new SecurityException();}
	
	@Override
	public void removeRange(int fromIndex, int toIndex) {throw new SecurityException();}
	
	@Override
	public void forEach(Consumer<? super Object> action) {throw new SecurityException();}
	
	@Override
	public boolean removeIf(Predicate<? super Object> filter) {throw new SecurityException();}
	
	@Override
	public Spliterator<Object> spliterator() {throw new SecurityException();}
}
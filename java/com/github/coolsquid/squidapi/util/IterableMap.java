/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.util;

import java.util.HashMap;
import java.util.Iterator;

public class IterableMap<E, T> extends HashMap<E, T> implements Iterable<E> {

	private static final long serialVersionUID = -3532104880584018554L;

	@Override
	public Iterator<E> iterator() {
		return this.keySet().iterator();
	}
}
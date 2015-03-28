/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.HashMap;
import java.util.Iterator;

public class IterableMap<E, T> extends HashMap<E, T> implements Iterable<E> {

	private static final long serialVersionUID = -527213307467096036L;

	@Override
	public Iterator<E> iterator() {
		return this.keySet().iterator();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.Collection;
import java.util.HashSet;

public class OneWaySet<E> extends HashSet<E> {

	private static final long serialVersionUID = -7807424869991903754L;

	public OneWaySet() {
		
	}

	public OneWaySet(Iterable<E> content) {
		for (E e: content) {
			this.add(e);
		}
	}

	public OneWaySet(E[] content) {
		for (E e: content) {
			this.add(e);
		}
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}
}
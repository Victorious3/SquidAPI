/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.registry;

import java.util.List;
import java.util.Map;

public class LockedRegistrySimple<E> extends RegistrySimple<E> {

	private boolean locked;

	public LockedRegistrySimple() {

	}

	@SafeVarargs
	private LockedRegistrySimple(E... values) {

	}

	public LockedRegistrySimple(List<E> list, Map<E, Integer> map) {
		super(list, map);
	}

	public LockedRegistrySimple<E> lock() {
		this.locked = true;
		return this;
	}

	public boolean isLocked() {
		return this.locked;
	}

	@Override
	public void register(E e) {
		if (this.locked) {
			throw new UnsupportedOperationException("The registry cannot be modified after it has been locked.");
		}
		super.register(e);
	}

	@Override
	public LockedRegistrySimple<E> clone() {
		return new LockedRegistrySimple<E>(this.getList(), this.getMap());
	}

	@SafeVarargs
	public static <T> LockedRegistrySimple<T> create(T... values) {
		return new LockedRegistrySimple<T>(values);
	}
}
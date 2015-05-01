/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import coolsquid.squidapi.util.Utils;


public class NamedRegistry<E> extends Registry<E> {

	@Override
	public void register(String name, E e) {
		if (name == null) {
			throw new NullPointerException();
		}
		super.register(Utils.getCurrentMod().getModId() + ':' + name, e);
	}

	@SafeVarargs
	public static <T> NamedRegistry<T> create(T... content) {
		NamedRegistry<T> result = new NamedRegistry<T>();
		for (T t: content) {
			result.register(t.toString(), t);
		}
		return result;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.interfaces;


public interface IFactory<E> {
	public E newInstance(String... params);
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util.collect;

import net.minecraft.block.Block;

public class BlockProperties<E> {

	private final Object[] a = new Object[4096];

	@SuppressWarnings("unchecked")
	public E get(Block block) {
		return (E) this.a[Block.getIdFromBlock(block)];
	}

	public void set(Block block, E value) {
		this.a[Block.getIdFromBlock(block)] = value;
	}

	public static <T> BlockProperties<T> create() {
		return new BlockProperties<T>();
	}
}
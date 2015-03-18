/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.DamageSource;

public enum DamageSourceRegistry {
	
	INFIRE(DamageSource.inFire),
	ONFIRE(DamageSource.onFire),
	LAVA(DamageSource.lava),
	INWALL(DamageSource.inWall),
	DROWN(DamageSource.drown),
	STARVE(DamageSource.starve),
	CACTUS(DamageSource.cactus),
	FALL(DamageSource.fall),
	OUTOFWORLD(DamageSource.outOfWorld),
	GENERIC(DamageSource.generic),
	MAGIC(DamageSource.magic),
	WITHER(DamageSource.wither),
	ANVIL(DamageSource.anvil),
	FALLINGBLOCK(DamageSource.fallingBlock);
	
	private final DamageSource damagesource;

	private DamageSourceRegistry(DamageSource damagesource) {
		this.damagesource = damagesource;
	}

	public DamageSource getDamageSource() {
		return this.damagesource;
	}
	
	public static List<DamageSource> getDamageSources() {
		List<DamageSource> list = Lists.newArrayList();
		for (DamageSourceRegistry a: values()) {
			list.add(a.getDamageSource());
		}
		return list;
	}
}
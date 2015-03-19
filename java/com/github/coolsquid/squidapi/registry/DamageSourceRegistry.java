/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.util.DamageSource;

public class DamageSourceRegistry extends Registry<DamageSource> {
	
	private static final DamageSourceRegistry instance = new DamageSourceRegistry();
	
	public DamageSourceRegistry() {
		this.register(DamageSource.anvil);
		this.register(DamageSource.cactus);
		this.register(DamageSource.drown);
		this.register(DamageSource.fall);
		this.register(DamageSource.fallingBlock);
		this.register(DamageSource.generic);
		this.register(DamageSource.inFire);
		this.register(DamageSource.inWall);
		this.register(DamageSource.lava);
		this.register(DamageSource.magic);
		this.register(DamageSource.onFire);
		this.register(DamageSource.outOfWorld);
		this.register(DamageSource.starve);
		this.register(DamageSource.wither);
	}

	public static DamageSourceRegistry instance() {
		return instance;
	}

	@Override
	public void register(DamageSource source) {
		this.register(source.damageType, source);
	}
}
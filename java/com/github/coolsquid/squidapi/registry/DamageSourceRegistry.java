/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.registry;

import net.minecraft.util.DamageSource;

public class DamageSourceRegistry extends RegistrySimple<DamageSource> {

	private static final DamageSourceRegistry instance = new DamageSourceRegistry();

	public static DamageSourceRegistry instance() {
		return instance;
	}

	public void register(DamageSource source) {
		this.register(source.damageType, source);
	}

	static {
		instance.register(DamageSource.anvil);
		instance.register(DamageSource.cactus);
		instance.register(DamageSource.drown);
		instance.register(DamageSource.fall);
		instance.register(DamageSource.fallingBlock);
		instance.register(DamageSource.generic);
		instance.register(DamageSource.inFire);
		instance.register(DamageSource.inWall);
		instance.register(DamageSource.lava);
		instance.register(DamageSource.magic);
		instance.register(DamageSource.onFire);
		instance.register(DamageSource.outOfWorld);
		instance.register(DamageSource.starve);
		instance.register(DamageSource.wither);
	}
}
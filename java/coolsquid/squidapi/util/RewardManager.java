/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import coolsquid.squidapi.SquidAPI;
import coolsquid.squidapi.handlers.CommonHandler;
import coolsquid.squidapi.registry.LockedRegistrySimple;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class RewardManager {

	public static final RewardManager INSTANCE = new RewardManager();

	private final LockedRegistrySimple<UUID> patreons = new LockedRegistrySimple<UUID>();

	private RewardManager() {
		
	}

	public void addPatreons(String... patreons) {
		if (Utils.getCaller() == CommonHandler.class) {
			for (int a = 0; a < patreons.length; a++) {
				this.patreons.register(UUID.fromString(patreons[a]));
			}
		}
		else {
			SquidAPI.instance().bigWarning("An unauthorized class tried to add Patreons to SquidAPI's Patreon list!");
			throw new SecurityException();
		}
	}

	public void addPatreons(UUID... patreons) {
		if (Utils.getCaller() == CommonHandler.class) {
			for (UUID id: patreons) {
				this.patreons.register(id);
			}
		}
		else {
			SquidAPI.instance().bigWarning("An unauthorized class tried to add Patreons to SquidAPI's Patreon list!");
			throw new SecurityException();
		}
	}

	public List<UUID> getPatreons() {
		return ImmutableList.copyOf(this.patreons);
	}

	public boolean isPatreon(UUID id) {
		return this.getPatreons().contains(id);
	}
}
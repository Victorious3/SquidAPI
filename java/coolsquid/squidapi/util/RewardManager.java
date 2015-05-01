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
import coolsquid.squidapi.util.collect.LockedRegistrySimple;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class RewardManager {

	public static final RewardManager INSTANCE = new RewardManager();

	private final LockedRegistrySimple<UUID> users = new LockedRegistrySimple<UUID>();

	private RewardManager() {

	}

	public void addSpecialUsers(String... users) {
		if (Utils.getCaller() == CommonHandler.class) {
			for (String user : users) {
				this.users.register(UUID.fromString(user));
			}
		}
		else {
			SquidAPI.instance().bigWarning("An unauthorized class tried to add SpecialUsers to SquidAPI's list of special users!");
			throw new SecurityException();
		}
		this.users.lock();
	}

	public void addSpecialUsers(UUID... users) {
		if (Utils.getCaller() == CommonHandler.class) {
			for (UUID id: users) {
				this.users.register(id);
			}
		}
		else {
			SquidAPI.instance().bigWarning("An unauthorized class tried to add SpecialUsers to SquidAPI's list of special users!");
			throw new SecurityException();
		}
		this.users.lock();
	}

	public List<UUID> getSpecialUsers() {
		return ImmutableList.copyOf(this.users);
	}

	public boolean isPatreon(UUID id) {
		return this.users.containsValue(id);
	}
}
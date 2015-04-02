/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.util;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.entity.EntityClientPlayerMP;

import com.google.common.collect.Lists;

import coolsquid.squidapi.SquidAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class PatreonController {

	public static final PatreonController INSTANCE = new PatreonController();

	private final List<UUID> patreons = Lists.newArrayList();

	private PatreonController() {
		
	}

	public void addPatreons(String... patreons) {
		UUID[] ids = new UUID[patreons.length];
		for (int a = 0; a < patreons.length; a++) {
			ids[a] = UUID.fromString(patreons[a]);
		}
		this.addPatreons(ids);
	}

	public void addPatreons(UUID... patreons) {
		if (Utils.getCaller() == SquidAPI.class) {
			for (UUID id: patreons) {
				this.patreons.add(id);
			}
		}
	}

	public List<UUID> getPatreons() {
		return this.patreons;
	}

	public boolean isPatreon(EntityClientPlayerMP player) {
		return this.getPatreons().contains(player.getGameProfile().getId());
	}
}
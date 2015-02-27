/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.helpers.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBans;
import net.minecraft.server.management.UserListEntry;

import com.mojang.authlib.GameProfile;

class BanHelper {

	private static final UserListBans list = MinecraftServer.getServer().getConfigurationManager().func_152608_h();
	
	public static void ban(UserListEntry entry) {
		list.func_152687_a(entry);
	}
	
	public static boolean isBanned(GameProfile profile) {
		return list.func_152702_a(profile);
	}
	
	public static UserListEntry getBannedPlayer(Object object) {
		return list.func_152683_b(object);
	}
	
	public static void unban(Object object) {
		list.func_152684_c(object);
	}
	
	public static String[] getBans() {
		return list.func_152685_a();
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListEntry;
import net.minecraft.server.management.UserListOps;

public class OpHelper {
	
	private static final UserListOps list = MinecraftServer.getServer().getConfigurationManager().func_152603_m();
	
	public static void op(UserListEntry entry) {
		list.func_152687_a(entry);
	}
	
	public static UserListEntry getOp(Object object) {
		return list.func_152683_b(object);
	}
	
	public static boolean isOp(String name) {
		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(ServerHelper.getPlayerFromName(name).getGameProfile()) || MinecraftServer.getServer().getServerOwner().equals(name);
	}
	
	public static void deop(Object object) {
		list.func_152684_c(object);
	}
	
	public static String[] getOps() {
		return list.func_152685_a();
	}
}
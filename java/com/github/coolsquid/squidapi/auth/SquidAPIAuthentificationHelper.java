/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi.auth;

import com.github.coolsquid.squidapi.registry.ProtectedList;

public class SquidAPIAuthentificationHelper {
	
	public static final ProtectedList modstocheck = new ProtectedList();
	public static final ProtectedList unauthorisedmods = new ProtectedList();
	
	@Deprecated
	public static void auth(String modid, String version, String newversionurl) {
		modstocheck.add(new AuthEntry(modid, version, newversionurl));
	}
	
	public static void auth(AuthEntry entry) {
		modstocheck.add(entry);
	}
}
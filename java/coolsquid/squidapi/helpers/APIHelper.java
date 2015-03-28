/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.helpers;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.ModContainer;

public class APIHelper implements Iterable<ModContainer> {

	public static final APIHelper INSTANCE = new APIHelper();

	private final Map<String, ModContainer> map = Maps.newHashMap();

	private APIHelper() {
		for (ModContainer api: ModAPIManager.INSTANCE.getAPIList()) {
			this.map.put(api.getModId(), api);
		}
	}

	public boolean containsAPI(Object key) {
		return this.map.containsKey(key);
	}

	public ModContainer getAPI(Object key) {
		return this.map.get(key);
	}

	@Override
	public Iterator<ModContainer> iterator() {
		return this.map.values().iterator();
	}
}
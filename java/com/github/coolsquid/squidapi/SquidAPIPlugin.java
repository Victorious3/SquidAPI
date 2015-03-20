/*******************************************************************************
 * Copyright (c) 2015 CoolSquid, ljfa.
 * All rights reserved.
 *******************************************************************************/
package com.github.coolsquid.squidapi;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class SquidAPIPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;

import coolsquid.squidapi.SquidAPI;


public enum EnumBlacklist {

	REIKA("Reika"),
	DRAGONAPI("DragonAPI"),
	ROTARYCRAFT("RotaryCraft"),
	CHROMATICRAFT("ChromatiCraft"),
	REACTORCRAFT("ReactorCraft"),
	ELECTRICRAFT("ElectriCraft");

	private final String name;

	private EnumBlacklist(String name) {
		this.name = name;
		SquidAPI.instance().info("Found blacklist entry: " + this.toString());
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
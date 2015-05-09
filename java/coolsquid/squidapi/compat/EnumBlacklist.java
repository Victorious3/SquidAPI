/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
package coolsquid.squidapi.compat;


public enum EnumBlacklist {

	REIKA("Reika"),
	ROTARYCRAFT("RotaryCraft"),
	CHROMATICRAFT("ChromatiCraft"),
	REACTORCRAFT("ReactorCraft"),
	ELECTRICRAFT("ElectriCraft");

	private final String name;

	private EnumBlacklist(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
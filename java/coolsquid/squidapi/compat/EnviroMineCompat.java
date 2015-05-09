/*******************************************************************************
 * Copyright (c) 2015 CoolSquid.
 * All rights reserved.
 *******************************************************************************/
/*package coolsquid.squidapi.compat;

import enviromine.core.EM_Settings;
import enviromine.trackers.properties.BlockProperties;

public class EnviroMineCompat {

	public static final EnviroMineCompat instance = new EnviroMineCompat();

	private EnviroMineCompat() {}

	public BlockProperties getPropertiesForBlock(String block) {
		if (!EM_Settings.blockProperties.containsKey(block)) {
			EM_Settings.blockProperties.put(block, new BlockProperties());
		}
		return EM_Settings.blockProperties.get(block);
	}

	public void setBlockTemperature(String block, float temp) {
		this.getPropertiesForBlock(block).temp = temp;
		this.getPropertiesForBlock(block).enableTemp = true;
	}

	public void setBlockSlide(String block, boolean value) {
		this.getPropertiesForBlock(block).wetSlide = true;
	}
}*/
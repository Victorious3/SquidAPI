package com.github.coolsquid.squidapi.biome;

import net.minecraft.world.biome.BiomeGenBase;

import com.github.coolsquid.squidapi.helpers.BiomeHelper;

public class BiomeBase extends BiomeGenBase {

	public BiomeBase() {
		super(BiomeHelper.findFreeId());
	}
}
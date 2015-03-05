package com.github.coolsquid.squidapi.world.biome;

import com.github.coolsquid.squidapi.helpers.IdHelper;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeBase extends BiomeGenBase {

	public BiomeBase(String biomename) {
		super(IdHelper.findFreeBiomeId(biomename));
		this.setBiomeName(biomename);
	}
}
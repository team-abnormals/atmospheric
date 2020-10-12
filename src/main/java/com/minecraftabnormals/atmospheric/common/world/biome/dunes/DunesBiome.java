package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;

public class DunesBiome extends AbstractDunesBiome {

	public DunesBiome(Biome.Builder builder) {
		super(builder);
	}

	@Override
	public Biome getHill(INoiseRandom rand) {
		return rand.random(5) > 1 ? AtmosphericBiomes.DUNES_HILLS.get() : AtmosphericBiomes.FLOURISHING_DUNES.get();
	}
}

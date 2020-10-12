package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import java.util.ArrayList;

import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.world.biome.Biome;

public class RainforestBiome extends AbstractRainforestBiome {
	
	public RainforestBiome(Biome.Builder builder) {
		super(builder);
	}

	@Override
	public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
		ArrayList<Biome> list = new ArrayList<Biome>();

		list.add(AtmosphericBiomes.RAINFOREST_BASIN.get());
		list.add(AtmosphericBiomes.RAINFOREST_PLATEAU.get());
		list.add(AtmosphericBiomes.SPARSE_RAINFOREST_PLATEAU.get());

		return list.get(rand.random(list.size()));
	}
}

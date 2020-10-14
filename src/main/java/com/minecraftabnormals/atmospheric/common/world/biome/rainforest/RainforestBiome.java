package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import java.util.ArrayList;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class RainforestBiome extends AbstractRainforestBiome {

	public RainforestBiome(Biome.Builder builder) {
		super(builder);
	}

	@Override
	public void addFeatures() {
		DefaultBiomeFeatures.func_235196_b_(this); // MINESHAFTS & STRONGHOLDS

		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);

		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);

		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addSprings(this);
		AtmosphericBiomeFeatures.addPodzol(this);
		DefaultBiomeFeatures.addTaigaRocks(this);

		DefaultBiomeFeatures.addMushrooms(this);
		DefaultBiomeFeatures.addReedsAndPumpkins(this);
		DefaultBiomeFeatures.addTaigaLargeFerns(this);
		AtmosphericBiomeFeatures.addRainforestTrees(this, 10, 1);
		AtmosphericBiomeFeatures.addRainforestDoublePlants(this);
		AtmosphericBiomeFeatures.addRainforestFlowers(this);
		AtmosphericBiomeFeatures.addRainforestFoliage(this);
		AtmosphericBiomeFeatures.addRainforestWaterFoliage(this);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
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

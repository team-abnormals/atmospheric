package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class SparseRainforestBasinBiome extends RainforestBiome {

	public SparseRainforestBasinBiome(Biome.Builder builder) {
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
		AtmosphericBiomeFeatures.addRainforestBasinTrees(this, 80, 20);
		AtmosphericBiomeFeatures.addRainforestDoublePlants(this);
		AtmosphericBiomeFeatures.addRainforestFlowers(this);
		AtmosphericBiomeFeatures.addRainforestFoliage(this);
		AtmosphericBiomeFeatures.addSparseRainforestBasinWaterFoliage(this);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}

package com.minecraftabnormals.atmospheric.common.world.biome.rainforest;

import java.util.ArrayList;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class RainforestBasinBiome extends AbstractRainforestBiome {
	
	public RainforestBasinBiome(Biome.Builder builder) {
		super(builder);
	}

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
		AtmosphericBiomeFeatures.addRainforestBasinTrees(this, 2600, 800);
		AtmosphericBiomeFeatures.addRainforestDoublePlants(this);
		AtmosphericBiomeFeatures.addRainforestFlowers(this);
		AtmosphericBiomeFeatures.addRainforestFoliage(this);
		AtmosphericBiomeFeatures.addRainforestBasinWaterFoliage(this);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}

	public void addSpawns() {
		super.addSpawns();
		addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
	}

	@Override
	public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
		ArrayList<Biome> list = new ArrayList<Biome>();

		list.add(AtmosphericBiomes.SPARSE_RAINFOREST_BASIN.get());
		list.add(AtmosphericBiomes.RAINFOREST_PLATEAU.get());

		return list.get(rand.random(list.size()));
	}
}

package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.INoiseRandom;

public class DunesBiome extends AbstractDunesBiome {

	public DunesBiome(Biome.Builder builder) {
		super(builder);
	}

	@Override
	public void addFeatures() {
		this.func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL_DESERT);
		DefaultBiomeFeatures.func_235196_b_(this); // MINESHAFTS & STRONGHOLDS

		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);

		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		AtmosphericBiomeFeatures.addFossils(this);
		DefaultBiomeFeatures.addMushrooms(this);

		AtmosphericBiomeFeatures.addDeadBushes(this, 2);
		AtmosphericBiomeFeatures.addYuccaTrees(this, 0, 0.25F, 1);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.075F, 2);
		AtmosphericBiomeFeatures.addAloeVera(this, 2);
		AtmosphericBiomeFeatures.addYuccaFlower(this, 2);
		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 2);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}

	@Override
	public Biome getHill(INoiseRandom rand) {
		return rand.random(5) > 1 ? AtmosphericBiomes.DUNES_HILLS.get() : AtmosphericBiomes.FLOURISHING_DUNES.get();
	}
}

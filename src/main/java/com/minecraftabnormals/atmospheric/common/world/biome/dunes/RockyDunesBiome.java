package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public final class RockyDunesBiome extends AbstractDunesBiome {
	
	public RockyDunesBiome(Biome.Builder builder) {
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

		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 4);
		AtmosphericBiomeFeatures.addDuneRocks(this, 2, 3);
		AtmosphericBiomeFeatures.addDeadBushes(this, 3);
		AtmosphericBiomeFeatures.addYuccaTrees(this, 0, 0.1F, 1);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.075F, 1);
		AtmosphericBiomeFeatures.addAloeVera(this, 2);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}

	@Override
	public Biome getHill(net.minecraft.world.gen.INoiseRandom rand) {
		return rand.random(5) > 1 ? AtmosphericBiomes.ROCKY_DUNES_HILLS.get() : AtmosphericBiomes.PETRIFIED_DUNES.get();
	}
}

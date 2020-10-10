package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public final class RockyDunesHillsBiome extends DunesBiome {
	
	public RockyDunesHillsBiome(Biome.Builder builder) {
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

		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 5);
		AtmosphericBiomeFeatures.addDuneRocks(this, 2, 3);
		AtmosphericBiomeFeatures.addDeadBushes(this, 4);
		AtmosphericBiomeFeatures.addYuccaTrees(this, 0, 0.1F, 1);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.075F, 1);
		AtmosphericBiomeFeatures.addAloeVera(this, 1);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}

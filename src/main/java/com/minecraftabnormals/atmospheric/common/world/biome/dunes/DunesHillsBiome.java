package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class DunesHillsBiome extends DunesBiome {

	public DunesHillsBiome(Biome.Builder builder) {
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

		AtmosphericBiomeFeatures.addDeadBushes(this, 3);
		AtmosphericBiomeFeatures.addYuccaTrees(this, 0, 0.1F, 1);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.075F, 2);
		AtmosphericBiomeFeatures.addAloeVera(this, 2);
		AtmosphericBiomeFeatures.addYuccaFlower(this, 2);
		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 3);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}

package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public final class PetrifiedDunesBiome extends AbstractDunesBiome {
	
	public PetrifiedDunesBiome(Biome.Builder builder) {
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

		AtmosphericBiomeFeatures.addDeadBushes(this, 5);
		DefaultBiomeFeatures.addMushrooms(this);
		AtmosphericBiomeFeatures.addPetrifiedYuccaTrees(this, 0, 0.5F, 2);
		AtmosphericBiomeFeatures.addSurfaceFossils(this);
		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 2);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.1F, 1);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}

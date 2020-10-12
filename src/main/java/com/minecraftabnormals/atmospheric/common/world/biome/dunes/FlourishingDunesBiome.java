package com.minecraftabnormals.atmospheric.common.world.biome.dunes;

import com.minecraftabnormals.atmospheric.common.world.biome.AtmosphericBiomeFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class FlourishingDunesBiome extends AbstractDunesBiome {

	public FlourishingDunesBiome(Biome.Builder builder) {
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
		AtmosphericBiomeFeatures.addDuneGrassPatches(this);

		AtmosphericBiomeFeatures.addDeadBushes(this, 4);
		AtmosphericBiomeFeatures.addMelons(this);
		DefaultBiomeFeatures.addMushrooms(this);
		AtmosphericBiomeFeatures.addBeehiveYuccaTrees(this, 0, 0.25F, 1);
		AtmosphericBiomeFeatures.addBabyYuccaTrees(this, 2, 0.05F, 1);
		AtmosphericBiomeFeatures.addGilias(this, 18);
		AtmosphericBiomeFeatures.addAloeVera(this, 6);
		AtmosphericBiomeFeatures.addBarrelCactus(this, 0, 0.5F, 4);
		AtmosphericBiomeFeatures.addDuneRocks(this, 1, 3);
		AtmosphericBiomeFeatures.addYuccaFlower(this, 5);

		DefaultBiomeFeatures.addFreezeTopLayer(this);
	}
}

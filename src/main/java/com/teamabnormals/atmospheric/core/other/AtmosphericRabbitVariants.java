package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericRabbitVariants extends BlueprintRabbitVariants {
	private static final int UNIQUE_OFFSET = 1337;

	public static final BlueprintRabbitVariant YELLOW = register(UNIQUE_OFFSET, Atmospheric.location("yellow"), context -> getBiome(context).is(AtmosphericBiomeTags.SPAWNS_YELLOW_RABBITS));
}
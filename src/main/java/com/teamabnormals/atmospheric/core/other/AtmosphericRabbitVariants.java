package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import com.teamabnormals.blueprint.core.events.LoadThisClassEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AtmosphericRabbitVariants extends BlueprintRabbitVariants {
	private static final int UNIQUE_OFFSET = 1337;

	public static final BlueprintRabbitVariant YELLOW = register(UNIQUE_OFFSET, Atmospheric.location("yellow"), context -> getBiome(context).is(AtmosphericBiomeTags.SPAWNS_YELLOW_RABBITS));

	@SubscribeEvent
	public static void $(LoadThisClassEvent event) {
	}
}
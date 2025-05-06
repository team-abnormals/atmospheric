package com.teamabnormals.atmospheric.core.other;

import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.core.registry.AtmosphericConditionSerializers;
import com.teamabnormals.blueprint.core.api.conditions.BlueprintAndCondition;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import static com.teamabnormals.atmospheric.core.AtmosphericConfig.COMMON;

public class AtmosphericConditions {
	public static final ModLoadedCondition NEAPOLITAN_LOADED = new ModLoadedCondition("neapolitan");
	public static final NotCondition NEAPOLITAN_NOT_LOADED = new NotCondition(NEAPOLITAN_LOADED);

	public static final ModLoadedCondition QUARK_LOADED = new ModLoadedCondition("quark");

	public static final ConfigValueCondition GRIMWOOD_ANCIENT_CITIES = config(COMMON.grimwoodAncientCities, "grimwood_ancient_cities");
	public static final ConfigValueCondition YUCCA_DESERT_VILLAGES = config(COMMON.yuccaDesertVillages, "yucca_desert_villages");

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(AtmosphericConditionSerializers.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}
}
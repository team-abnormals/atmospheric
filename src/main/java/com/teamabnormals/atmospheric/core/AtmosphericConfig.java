package com.teamabnormals.atmospheric.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class AtmosphericConfig {

	public static class Common {
		@ConfigKey("yucca_desert_villages")
		public final ConfigValue<Boolean> yuccaDesertVillages;
		@ConfigKey("grimwood_ancient_cities")
		public final ConfigValue<Boolean> grimwoodAncientCities;

		public Common(ModConfigSpec.Builder builder) {
			builder.push("generation");
			builder.push("structures");
			this.yuccaDesertVillages = builder.comment("If Jungle in Desert Villages is replaced with Yucca").define("Yucca Desert Villages", false);
			this.grimwoodAncientCities = builder.comment("If Dark Oak in Ancient Cities is replaced with Grimwood").define("Grimwood Ancient Cities", false);
			builder.pop();
			builder.pop();
		}
	}

	public static final ModConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
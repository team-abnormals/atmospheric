package com.minecraftabnormals.atmospheric.core.other;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class AtmosphericConfig {

	public static class Common {
		public final ConfigValue<Integer> rainforestWeight;
		public final ConfigValue<Integer> rainforestMountainsWeight;
		public final ConfigValue<Integer> dunesWeight;
		public final ConfigValue<Integer> rockyDunesWeight;
		
		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Common configurations for Atmospheric")
			.push("common");	
			
			builder.comment("Values for biome frequencies; lower = more rare. (Requires restart)")
			.push("biomeWeights");
			
			rainforestWeight = builder
				.comment("The frequency of Rainforests; Default: 2")
				.define("rainforestWeight", 2);
			
			rainforestMountainsWeight = builder
	                .comment("The frequency of Rainforest Mountains; Default: 1")
	                .define("rainforestMountainsWeight", 1);
			
			dunesWeight = builder
				.comment("The frequency of Dunes; Default: 5")
				.define("dunesWeight", 5);
			
			rockyDunesWeight = builder
					.comment("The frequency of Rocky Dunes; Default: 4")
					.define("rockyDunesWeight", 4);

			builder.pop();
			builder.pop();
		}
	}
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
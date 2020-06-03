package com.bagel.atmospheric.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.config.ModConfig;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class AtmosphericConfig {

	public static class Common {
		public final ConfigValue<Integer> rosewoodForestWeight;
		public final ConfigValue<Integer> dunesWeight;
		public final ConfigValue<Integer> rockyDunesWeight;
		
		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Common configurations for Atmospheric")
			.push("common");	
			
			builder.comment("Values for biome frequencies; lower = more rare. (Requires restart)")
			.push("biomeWeights");
			
			rosewoodForestWeight = builder
				.comment("The frequency of Rosewood Forests; Default: 2")
				.define("rosewoodForestWeight", 2);
			
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
	
	public static class ValuesHolder {
		private static int rosewoodForestWeight;
		private static int dunesWeight;
		private static int rockyDunesWeight;

		public static void updateCommonValuesFromConfig(ModConfig config) {
			rosewoodForestWeight = AtmosphericConfig.COMMON.rosewoodForestWeight.get();
			dunesWeight = AtmosphericConfig.COMMON.dunesWeight.get();
			rockyDunesWeight = AtmosphericConfig.COMMON.rockyDunesWeight.get();
		}

		public static int getRosewoodForestWeight() {
			return rosewoodForestWeight;
		}
		
		public static int getRockyDunesWeight() {
			return dunesWeight;
		}
		
		public static int getDunesWeight() {
			return rockyDunesWeight;
		}

	}
 
}
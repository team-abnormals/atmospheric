package com.minecraftabnormals.atmospheric.core;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class AtmosphericConfig {

	public static class Common {
		public final ConfigValue<Integer> rainforestWeight;
		public final ConfigValue<Integer> rainforestMountainsWeight;
		public final ConfigValue<Integer> rainforestPlateauWeight;
		public final ConfigValue<Integer> sparseRainforestPlateauWeight;
		public final ConfigValue<Integer> rainforestBasinWeight;
		public final ConfigValue<Integer> sparseRainforestBasinWeight;
		
		public final ConfigValue<Integer> dunesWeight;
		public final ConfigValue<Integer> dunesHillsWeight;
		public final ConfigValue<Integer> flourishingDunesWeight;
		public final ConfigValue<Integer> rockyDunesWeight;
		public final ConfigValue<Integer> rockyDunesHillsWeight;
		public final ConfigValue<Integer> petrifiedDunesWeight;
		
		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Common configurations for Atmospheric")
			.push("common");	
			
			builder.comment("Values for biome frequencies; lower = more rare. (Requires restart)", 
			        "If a biome has a default weight of 0, it generates as a sub-biome and not on its own.")
			.push("biome_weights");
			
			builder.comment("Rainforest biome weights")
            .push("rainforest");
			
			rainforestWeight = builder.define("Rainforest weight", 1);
			rainforestMountainsWeight = builder.define("Rainforest Mountains weight", 1);
			rainforestPlateauWeight = builder.define("Rainforest Plateau weight", 0);
			sparseRainforestPlateauWeight = builder.define("Sparse Rainforest Plateau weight", 0);
			rainforestBasinWeight = builder.define("Rainforest Basin weight", 1);
			sparseRainforestBasinWeight = builder.define("Sparse Rainforest Basin weight", 0);
			
			builder.pop();
			builder.comment("Dunes biome weights")
            .push("dunes");
			
			dunesWeight = builder.define("Dunes weight", 5);
			dunesHillsWeight = builder.define("Dunes Hills weight", 0);
			flourishingDunesWeight = builder.define("Flourishing Dunes weight", 0);
			rockyDunesWeight = builder.define("Rocky Dunes weight", 4);
			rockyDunesHillsWeight = builder.define("Rocky Dunes Hills weight", 0);
			petrifiedDunesWeight = builder.define("Petrified Dunes weight", 0);

			builder.pop();
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
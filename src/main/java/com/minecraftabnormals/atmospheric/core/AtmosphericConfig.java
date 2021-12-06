package com.minecraftabnormals.atmospheric.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

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

		public final ConfigValue<Integer> hotSpringsWeight;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("world");
			builder.push("biomes");

			builder.push("rainforest");
			rainforestWeight = builder.define("Rainforest weight", 1);
			rainforestMountainsWeight = builder.define("Rainforest Mountains weight", 1);
			rainforestPlateauWeight = builder.define("Rainforest Plateau weight", 0);
			sparseRainforestPlateauWeight = builder.define("Sparse Rainforest Plateau weight", 0);
			rainforestBasinWeight = builder.define("Rainforest Basin weight", 1);
			sparseRainforestBasinWeight = builder.define("Sparse Rainforest Basin weight", 0);
			builder.pop();

			builder.push("dunes");
			dunesWeight = builder.define("Dunes weight", 5);
			dunesHillsWeight = builder.define("Dunes Hills weight", 0);
			flourishingDunesWeight = builder.define("Flourishing Dunes weight", 0);
			rockyDunesWeight = builder.define("Rocky Dunes weight", 4);
			rockyDunesHillsWeight = builder.define("Rocky Dunes Hills weight", 0);
			petrifiedDunesWeight = builder.define("Petrified Dunes weight", 0);
			builder.pop();

			builder.comment("These biomes are experimental and not ready for use in gameplay").push("experimental");
			builder.push("hot_springs");
			hotSpringsWeight = builder.define("Hot Springs weight", 0);
			builder.pop();
			builder.pop();

			builder.pop();
			builder.pop();
		}
	}

	public static class Client {
		public final ConfigValue<Boolean> showUnobtainableDescription;

		public Client(ForgeConfigSpec.Builder builder) {
			builder.push("misc");
			this.showUnobtainableDescription = builder.comment("If unimplemented items should show that they are unobtainable in their item description").define("Show unobtainable description", true);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();

		Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = clientSpecPair.getRight();
		CLIENT = clientSpecPair.getLeft();
	}
}
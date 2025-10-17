package com.teamabnormals.atmospheric.core.registry.datapack;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.common.world.modification.ModdedBiomeSlice;
import com.teamabnormals.blueprint.core.registry.BlueprintBiomes;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.blueprint.core.util.BiomeUtil.MultiNoiseModdedBiomeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes.*;

public final class AtmosphericBiomeSlices {
	public static final ResourceKey<ModdedBiomeSlice> RAINFOREST_SLICE = createKey("rainforest");
	public static final ResourceKey<ModdedBiomeSlice> DUNES_AND_SCRUBLAND_SLICE = createKey("dunes_and_scrubland");
	public static final ResourceKey<ModdedBiomeSlice> SPINY_THICKET_SLICE = createKey("spiny_thicket");
	public static final ResourceKey<ModdedBiomeSlice> ASPEN_SLICE = createKey("aspen");
	public static final ResourceKey<ModdedBiomeSlice> LAUREL_SLICE = createKey("laurel");
	public static final ResourceKey<ModdedBiomeSlice> KOUSA_SLICE = createKey("kousa");

	public static final ResourceKey<Biome> RAINFOREST_AREA = AtmosphericBiomes.createKey("rainforest_area");
	public static final ResourceKey<Biome> SPARSE_RAINFOREST_AREA = AtmosphericBiomes.createKey("sparse_rainforest_area");
	public static final ResourceKey<Biome> RAINFOREST_BASIN_AREA = AtmosphericBiomes.createKey("rainforest_basin_area");
	public static final ResourceKey<Biome> SPARSE_RAINFOREST_BASIN_AREA = AtmosphericBiomes.createKey("sparse_rainforest_basin_area");
	public static final ResourceKey<Biome> DUNES_AREA = AtmosphericBiomes.createKey("dunes_area");
	public static final ResourceKey<Biome> FLOURISHING_DUNES_AREA = AtmosphericBiomes.createKey("flourishing_dunes_area");
	public static final ResourceKey<Biome> ROCKY_DUNES_AREA = AtmosphericBiomes.createKey("rocky_dunes_area");
	public static final ResourceKey<Biome> PETRIFIED_DUNES_AREA = AtmosphericBiomes.createKey("petrified_dunes_area");
	public static final ResourceKey<Biome> SPINY_THICKET_AREA = AtmosphericBiomes.createKey("spiny_thicket_area");
	public static final ResourceKey<Biome> SCRUBLAND_AREA = AtmosphericBiomes.createKey("scrubland_area");
	public static final ResourceKey<Biome> SNOWY_SCRUBLAND_AREA = AtmosphericBiomes.createKey("snowy_scrubland_area");
	public static final ResourceKey<Biome> ASPEN_PARKLAND_AREA = AtmosphericBiomes.createKey("aspen_parkland_area");
	public static final ResourceKey<Biome> LAUREL_FOREST_AREA = AtmosphericBiomes.createKey("laurel_forest_area");
	public static final ResourceKey<Biome> KOUSA_JUNGLE_AREA = AtmosphericBiomes.createKey("kousa_jungle_area");

	public static void bootstrap(BootstrapContext<ModdedBiomeSlice> context) {
		List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> entries = new ArrayList<>();
		new AtmosphericBiomeBuilder().addBiomes(entries::add);

		context.register(RAINFOREST_SLICE, new ModdedBiomeSlice(30, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(RAINFOREST_AREA, RAINFOREST)
				.area(SPARSE_RAINFOREST_AREA, SPARSE_RAINFOREST)
				.area(RAINFOREST_BASIN_AREA, RAINFOREST_BASIN)
				.area(SPARSE_RAINFOREST_BASIN_AREA, SPARSE_RAINFOREST_BASIN)
				.build(), LevelStem.OVERWORLD));

		context.register(DUNES_AND_SCRUBLAND_SLICE, new ModdedBiomeSlice(35, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(DUNES_AREA, DUNES)
				.area(FLOURISHING_DUNES_AREA, FLOURISHING_DUNES)
				.area(ROCKY_DUNES_AREA, ROCKY_DUNES)
				.area(PETRIFIED_DUNES_AREA, PETRIFIED_DUNES)
				.area(SCRUBLAND_AREA, SCRUBLAND)
				.area(SNOWY_SCRUBLAND_AREA, SNOWY_SCRUBLAND)
				.build(), LevelStem.OVERWORLD));

		context.register(SPINY_THICKET_SLICE, new ModdedBiomeSlice(30, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(SPINY_THICKET_AREA, SPINY_THICKET).build(), LevelStem.OVERWORLD));

		context.register(ASPEN_SLICE, new ModdedBiomeSlice(30, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(ASPEN_PARKLAND_AREA, ASPEN_PARKLAND).build(), LevelStem.OVERWORLD));

		context.register(LAUREL_SLICE, new ModdedBiomeSlice(30, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(LAUREL_FOREST_AREA, LAUREL_FOREST).build(), LevelStem.OVERWORLD));

		context.register(KOUSA_SLICE, new ModdedBiomeSlice(30, MultiNoiseModdedBiomeProvider.builder().biomes(entries::forEach)
				.area(KOUSA_JUNGLE_AREA, KOUSA_JUNGLE).build(), LevelStem.OVERWORLD));
	}

	public static ResourceKey<ModdedBiomeSlice> createKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.MODDED_BIOME_SLICES, Atmospheric.location(name));
	}

	//Modified version of OverworldBiomeBuilder to simplify Atmospheric's slice
	@SuppressWarnings("unchecked")
	private static final class AtmosphericBiomeBuilder {
		private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
		private final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
		private final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F)};
		private final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
		private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
		private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
		private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
		private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
		private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
		private final ResourceKey<Biome> VANILLA = BlueprintBiomes.ORIGINAL_SOURCE_MARKER;
		private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
				{SNOWY_SCRUBLAND_AREA, VANILLA, VANILLA, KOUSA_JUNGLE_AREA, VANILLA},
				{VANILLA, VANILLA, VANILLA, VANILLA, ASPEN_PARKLAND_AREA},
				{VANILLA, VANILLA, VANILLA, LAUREL_FOREST_AREA, ASPEN_PARKLAND_AREA},
				{VANILLA, VANILLA, VANILLA, RAINFOREST_AREA, RAINFOREST_AREA},
				{SCRUBLAND_AREA, SCRUBLAND_AREA, SCRUBLAND_AREA, DUNES_AREA, FLOURISHING_DUNES_AREA}};
		private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null}};
		private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
				{SNOWY_SCRUBLAND_AREA, VANILLA, KOUSA_JUNGLE_AREA, KOUSA_JUNGLE_AREA, KOUSA_JUNGLE_AREA},
				{VANILLA, VANILLA, VANILLA, VANILLA, ASPEN_PARKLAND_AREA},
				{VANILLA, VANILLA, LAUREL_FOREST_AREA, LAUREL_FOREST_AREA, ASPEN_PARKLAND_AREA},
				{VANILLA, VANILLA, VANILLA, RAINFOREST_AREA, RAINFOREST_AREA},
				{SCRUBLAND_AREA, SCRUBLAND_AREA, ROCKY_DUNES_AREA, ROCKY_DUNES_AREA, ROCKY_DUNES_AREA}};
		private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, SPARSE_RAINFOREST_AREA},
				{SCRUBLAND_AREA, SCRUBLAND_AREA, PETRIFIED_DUNES_AREA, null, null}};
		private final ResourceKey<Biome>[][] SHATTERED_BIOMES = new ResourceKey[][]{
				{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA},
				{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA},
				{VANILLA, VANILLA, VANILLA, VANILLA, VANILLA},
				{null, null, null, RAINFOREST_AREA, RAINFOREST_AREA},
				{null, null, null, null, null}};

		private void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			this.addOffCoastBiomes(consumer);
			this.addInlandBiomes(consumer);
			this.addUndergroundBiomes(consumer);
		}

		private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(-1.2F, -0.35F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, VANILLA);
			this.addSurfaceBiome(consumer, Climate.Parameter.span(-1.0F, 0.2F), this.FULL_RANGE, Climate.Parameter.span(-0.35F, -0.19F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, VANILLA);
			this.addSurfaceBiome(consumer, this.temperatures[3], Climate.Parameter.span(0.1F, 1.0F), Climate.Parameter.span(-0.35F, -0.19F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, RAINFOREST_BASIN_AREA);
			this.addSurfaceBiome(consumer, this.temperatures[4], Climate.Parameter.span(0.1F, 1.0F), Climate.Parameter.span(-0.35F, -0.19F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, VANILLA);
		}

		private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			this.addMidSlice(consumer, Climate.Parameter.span(-1.0F, -0.93333334F));
			this.addHighSlice(consumer, Climate.Parameter.span(-0.93333334F, -0.7666667F));
			this.addPeaks(consumer, Climate.Parameter.span(-0.7666667F, -0.56666666F));
			this.addHighSlice(consumer, Climate.Parameter.span(-0.56666666F, -0.4F));
			this.addMidSlice(consumer, Climate.Parameter.span(-0.4F, -0.26666668F));
			this.addLowSlice(consumer, Climate.Parameter.span(-0.26666668F, -0.05F));
			this.addValleys(consumer, Climate.Parameter.span(-0.05F, 0.05F));
			this.addLowSlice(consumer, Climate.Parameter.span(0.05F, 0.26666668F));
			this.addMidSlice(consumer, Climate.Parameter.span(0.26666668F, 0.4F));
			this.addHighSlice(consumer, Climate.Parameter.span(0.4F, 0.56666666F));
			this.addPeaks(consumer, Climate.Parameter.span(0.56666666F, 0.7666667F));
			this.addHighSlice(consumer, Climate.Parameter.span(0.7666667F, 0.93333334F));
			this.addMidSlice(consumer, Climate.Parameter.span(0.93333334F, 1.0F));
		}

		private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			for (int i = 0; i < this.temperatures.length; ++i) {
				Climate.Parameter climate$parameter = this.temperatures[i];

				for (int j = 0; j < this.humidities.length; ++j) {
					Climate.Parameter climate$parameter1 = this.humidities[j];
					ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrDunesIfHot(i, j, weirdness);
					ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrDunesIfHotOrSlopeIfCold(i, j, weirdness);
					ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptRainforestBiome(i, j, weirdness, resourcekey4);
					ResourceKey<Biome> resourcekey6 = this.pickPeakBiome(i, j, weirdness);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, resourcekey6);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey2);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey6);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, resourcekey3);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, resourcekey1);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, resourcekey3);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey5);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey4);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, resourcekey);
				}
			}
		}

		private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			for (int i = 0; i < this.temperatures.length; ++i) {
				Climate.Parameter climate$parameter = this.temperatures[i];

				for (int j = 0; j < this.humidities.length; ++j) {
					Climate.Parameter climate$parameter1 = this.humidities[j];
					ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrDunesIfHot(i, j, weirdness);
					ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrDunesIfHotOrSlopeIfCold(i, j, weirdness);
					ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey4 = this.pickShatteredBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey5 = this.maybePickWindsweptRainforestBiome(i, j, weirdness, resourcekey);
					ResourceKey<Biome> resourcekey6 = this.pickSlopeBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey7 = this.pickPeakBiome(i, j, weirdness);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[0], weirdness, 0.0F, resourcekey6);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, resourcekey7);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[1], weirdness, 0.0F, resourcekey2);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey6);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, resourcekey3);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, resourcekey1);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, resourcekey3);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey5);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey4);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, resourcekey);
				}
			}
		}

		private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, VANILLA);

			this.addSurfaceBiome(consumer, Climate.Parameter.span(-1.0F, 0.2F), FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, VANILLA);
			this.addSurfaceBiome(consumer, Climate.Parameter.span(0.2F, 0.6F), Climate.Parameter.span(0.1F, 1.0F), Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, RAINFOREST_BASIN_AREA);
			this.addSurfaceBiome(consumer, Climate.Parameter.span(0.6F, 1.0F), FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, VANILLA);

			for (int i = 0; i < this.temperatures.length; ++i) {
				Climate.Parameter climate$parameter = this.temperatures[i];

				for (int j = 0; j < this.humidities.length; ++j) {
					Climate.Parameter climate$parameter1 = this.humidities[j];
					ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrDunesIfHot(i, j, weirdness);
					ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrDunesIfHotOrSlopeIfCold(i, j, weirdness);
					ResourceKey<Biome> resourcekey3 = this.pickShatteredBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey4 = this.pickPlateauBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey5 = this.pickBeachBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey6 = this.maybePickWindsweptRainforestBiome(i, j, weirdness, resourcekey);
					ResourceKey<Biome> resourcekey7 = this.pickShatteredCoastBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey8 = this.pickSlopeBiome(i, j, weirdness);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, resourcekey8);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey2);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? resourcekey8 : resourcekey4);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey1);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey4);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, resourcekey1);
					if (weirdness.max() < 0L) {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, resourcekey5);
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
					} else {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
					}

					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey7);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey6);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey3);
					if (weirdness.max() < 0L) {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, resourcekey5);
					} else {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, resourcekey);
					}

					if (i == 0) {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, resourcekey);
					}
				}
			}
		}

		private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, VANILLA);

			this.addSurfaceBiome(consumer, Climate.Parameter.span(-1.0F, 0.2F), FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, VANILLA);
			this.addSurfaceBiome(consumer, Climate.Parameter.span(0.2F, 0.6F), Climate.Parameter.span(0.1F, 1.0F), Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, RAINFOREST_BASIN_AREA);
			this.addSurfaceBiome(consumer, Climate.Parameter.span(0.6F, 1.0F), FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, VANILLA);

			for (int i = 0; i < this.temperatures.length; ++i) {
				Climate.Parameter climate$parameter = this.temperatures[i];

				for (int j = 0; j < this.humidities.length; ++j) {
					Climate.Parameter climate$parameter1 = this.humidities[j];
					ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrDunesIfHot(i, j, weirdness);
					ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrDunesIfHotOrSlopeIfCold(i, j, weirdness);
					ResourceKey<Biome> resourcekey3 = this.pickBeachBiome(i, j, weirdness);
					ResourceKey<Biome> resourcekey4 = this.maybePickWindsweptRainforestBiome(i, j, weirdness, resourcekey);
					ResourceKey<Biome> resourcekey5 = this.pickShatteredCoastBiome(i, j, weirdness);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, resourcekey1);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, resourcekey2);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, resourcekey1);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0.0F, resourcekey3);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey5);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey4);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey);
					this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, resourcekey3);
					if (i == 0) {
						this.addSurfaceBiome(consumer, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, resourcekey);
					}
				}
			}
		}

		private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
			this.addRiverBiomes(consumer, Climate.Parameter.span(-1.0F, 0.2F), FULL_RANGE, weirdness, VANILLA);
			this.addRiverBiomes(consumer, Climate.Parameter.span(0.2F, 0.6F), Climate.Parameter.span(0.1F, 1.0F), weirdness, RAINFOREST_BASIN_AREA);
			this.addRiverBiomes(consumer, Climate.Parameter.span(0.6F, 1.0F), FULL_RANGE, weirdness, VANILLA);

			for (int temperatureIndex = 0; temperatureIndex < this.temperatures.length; ++temperatureIndex) {
				Climate.Parameter temperature = this.temperatures[temperatureIndex];
				for (int humidityIndex = 0; humidityIndex < this.humidities.length; ++humidityIndex) {
					Climate.Parameter humidity = this.humidities[humidityIndex];
					ResourceKey<Biome> resourcekey = this.pickMiddleBiomeOrDunesIfHot(temperatureIndex, humidityIndex, weirdness);
					this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, resourcekey);
				}
			}
		}

		private void addRiverBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter weirdness, ResourceKey<Biome> biome) {
			this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? VANILLA : biome);
			this.addSurfaceBiome(consumer, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, biome);
			this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, biome);
			this.addSurfaceBiome(consumer, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, biome);
			this.addSurfaceBiome(consumer, temperature, humidity, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, biome);
		}

		private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
			this.addUndergroundBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, VANILLA);
			this.addUndergroundBiome(consumer, this.FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, VANILLA);
		}

		private ResourceKey<Biome> pickMiddleBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			if (weirdness.max() < 0L) {
				return this.MIDDLE_BIOMES[temperatureIndex][humidityIndex];
			} else {
				ResourceKey<Biome> resourcekey = this.MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
				return resourcekey == null ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : resourcekey;
			}
		}

		private ResourceKey<Biome> pickMiddleBiomeOrDunesIfHot(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			return temperatureIndex == 4 ? this.pickDuneBiome(humidityIndex, weirdness) : this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness);
		}

		private ResourceKey<Biome> pickMiddleBiomeOrDunesIfHotOrSlopeIfCold(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			return temperatureIndex == 0 ? this.pickSlopeBiome(temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeOrDunesIfHot(temperatureIndex, humidityIndex, weirdness);
		}

		private ResourceKey<Biome> maybePickWindsweptRainforestBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> biome) {
			if (weirdness.max() >= 0L) {
				if (temperatureIndex == 3 && humidityIndex > 1) return RAINFOREST_AREA;
				if (temperatureIndex > 1 && humidityIndex < 4) return VANILLA;
			}
			return biome;
		}

		private ResourceKey<Biome> pickShatteredCoastBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			ResourceKey<Biome> resourcekey = weirdness.max() >= 0L ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiome(temperatureIndex, humidityIndex, weirdness);
			return this.maybePickWindsweptRainforestBiome(temperatureIndex, humidityIndex, weirdness, resourcekey);
		}

		private ResourceKey<Biome> pickBeachBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			if (temperatureIndex == 0) {
				return VANILLA;
			} else {
				if (temperatureIndex == 3 && humidityIndex > 2) return weirdness.max() >= 0L ? RAINFOREST_BASIN_AREA : SPARSE_RAINFOREST_BASIN_AREA;
				return VANILLA;
			}
		}

		//Replaces pickBadlandsBiome because badlands biomes can act as middle biome deserts
		private ResourceKey<Biome> pickDuneBiome(int humidityIndex, Climate.Parameter weirdness) {
			if (humidityIndex < 1) {
				return weirdness.max() < 0L ? PETRIFIED_DUNES_AREA : ROCKY_DUNES_AREA;
			} else if (humidityIndex < 3) {
				return DUNES_AREA;
			} else {
				return humidityIndex < 4 ? FLOURISHING_DUNES_AREA : SPINY_THICKET_AREA;
			}
		}

		private ResourceKey<Biome> pickPlateauBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			if (weirdness.max() < 0L) {
				return this.PLATEAU_BIOMES[temperatureIndex][humidityIndex];
			} else {
				ResourceKey<Biome> resourcekey = this.PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex];
				return resourcekey == null ? this.PLATEAU_BIOMES[temperatureIndex][humidityIndex] : resourcekey;
			}
		}

		private ResourceKey<Biome> pickPeakBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			if (temperatureIndex <= 2) {
				return VANILLA;
			} else {
				if (temperatureIndex == 3) return VANILLA;
				return humidityIndex < 2 && weirdness.max() < 0L ? PETRIFIED_DUNES_AREA : ROCKY_DUNES_AREA;
			}
		}

		private ResourceKey<Biome> pickSlopeBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			if (temperatureIndex >= 3) {
				return this.pickPlateauBiome(temperatureIndex, humidityIndex, weirdness);
			} else {
				return VANILLA;
			}
		}

		private ResourceKey<Biome> pickShatteredBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
			ResourceKey<Biome> resourcekey = this.SHATTERED_BIOMES[temperatureIndex][humidityIndex];
			return resourcekey == null ? this.pickMiddleBiome(temperatureIndex, humidityIndex, weirdness) : resourcekey;
		}

		private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter p_187184_, Climate.Parameter p_187185_, Climate.Parameter p_187186_, float p_187187_, ResourceKey<Biome> p_187188_) {
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, p_187184_, p_187185_, Climate.Parameter.point(0.0F), p_187186_, p_187187_), p_187188_));
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, p_187184_, p_187185_, Climate.Parameter.point(1.0F), p_187186_, p_187187_), p_187188_));
		}

		private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter p_187204_, Climate.Parameter p_187205_, Climate.Parameter p_187206_, float p_187207_, ResourceKey<Biome> p_187208_) {
			consumer.accept(Pair.of(Climate.parameters(temperature, humidity, p_187204_, p_187205_, Climate.Parameter.span(0.2F, 0.9F), p_187206_, p_187207_), p_187208_));
		}
	}

}

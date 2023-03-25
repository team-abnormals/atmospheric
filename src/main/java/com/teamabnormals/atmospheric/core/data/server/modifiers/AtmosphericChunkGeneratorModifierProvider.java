package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.world.modification.chunk.ChunkGeneratorModifierProvider;
import com.teamabnormals.blueprint.common.world.modification.chunk.modifiers.SurfaceRuleModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public final class AtmosphericChunkGeneratorModifierProvider extends ChunkGeneratorModifierProvider {

	public AtmosphericChunkGeneratorModifierProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		ConditionSource isDunes = isBiome(AtmosphericBiomes.DUNES.getKey(), AtmosphericBiomes.FLOURISHING_DUNES.getKey(), AtmosphericBiomes.ROCKY_DUNES.getKey(), AtmosphericBiomes.PETRIFIED_DUNES.getKey());
		ConditionSource isSpinyThicket = isBiome(AtmosphericBiomes.SPINY_THICKET.getKey());
		ConditionSource isShrubland = isBiome(AtmosphericBiomes.SHRUBLAND.getKey(), AtmosphericBiomes.SNOWY_SHRUBLAND.getKey());
		ConditionSource isHotSprings = isBiome(AtmosphericBiomes.HOT_SPRINGS.getKey());

		RuleSource coarseDirt = state(Blocks.COARSE_DIRT.defaultBlockState());
		RuleSource dirt = state(Blocks.DIRT.defaultBlockState());

		RuleSource sand = state(Blocks.SAND.defaultBlockState());
		RuleSource sandstone = state(Blocks.SANDSTONE.defaultBlockState());
		RuleSource redSand = state(Blocks.RED_SAND.defaultBlockState());
		RuleSource redSandstone = state(Blocks.RED_SANDSTONE.defaultBlockState());
		RuleSource aridSand = state(AtmosphericBlocks.ARID_SAND.get().defaultBlockState());
		RuleSource aridSandstone = state(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState());
		RuleSource redAridSand = state(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState());
		RuleSource redAridSandstone = state(AtmosphericBlocks.RED_ARID_SANDSTONE.get().defaultBlockState());

		RuleSource ivoryTravertine = state(AtmosphericBlocks.IVORY_TRAVERTINE.get().defaultBlockState());
		RuleSource peachTravertine = state(AtmosphericBlocks.PEACH_TRAVERTINE.get().defaultBlockState());
		RuleSource persimmonTravertine = state(AtmosphericBlocks.PERSIMMON_TRAVERTINE.get().defaultBlockState());
		RuleSource saffronTravertine = state(AtmosphericBlocks.SAFFRON_TRAVERTINE.get().defaultBlockState());

		RuleSource sandRuleSource = sandRuleSource(sand, sandstone);
		RuleSource redSandRuleSource = sandRuleSource(redSand, redSandstone);
		RuleSource aridSandRuleSource = sandRuleSource(aridSand, aridSandstone);
		RuleSource redAridSandRuleSource = sandRuleSource(redAridSand, redAridSandstone);

		RuleSource hotSpringsRuleSource = ifTrue(ON_FLOOR, sequence(ifTrue(noiseRange(0.9F, 1.9F), saffronTravertine), ifTrue(noiseRange(0.4F, 2.4F), persimmonTravertine), ifTrue(noiseRange(-0.2F, 3.0F), peachTravertine), ivoryTravertine));
		RuleSource hotSpringsRuleSource2 = ifTrue(UNDER_FLOOR, sequence(ifTrue(noiseRange(0.9F, 1.9F), saffronTravertine), ifTrue(noiseRange(0.4F, 2.4F), persimmonTravertine), ifTrue(noiseRange(-0.2F, 3.0F), peachTravertine), ivoryTravertine));

		this.entry("atmospheric_surface_rule").selects("minecraft:overworld")
				.addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(isDunes, sequence(ifTrue(noiseRange(0.3F, 2.5F), redAridSandRuleSource), aridSandRuleSource))), false))
				.addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(isSpinyThicket, sequence(ifTrue(noiseRange(0.1F, 2.5F), redAridSandRuleSource), redSandRuleSource))), false))
				.addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(isShrubland, sequence(ifTrue(noiseRange(-2.0F, -0.5F), aridSandRuleSource), ifTrue(noiseRange(1.0F, 2.5F), aridSandRuleSource), sandRuleSource))), false))
				.addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(isBiome(AtmosphericBiomes.ASPEN_PARKLAND.getKey()), ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), sequence(ifTrue(waterBlockCheck(0, 0), coarseDirt), dirt))))), false))
				.addModifier(new SurfaceRuleModifier(ifTrue(abovePreliminarySurface(), ifTrue(isHotSprings, ifTrue(not(yBlockCheck(VerticalAnchor.absolute(93), 0)), sequence(hotSpringsRuleSource, hotSpringsRuleSource2)))), false));
	}
	
	private RuleSource sandRuleSource(RuleSource sand, RuleSource sandstone) {
		return sequence(
				ifTrue(ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), sequence(ifTrue(ON_CEILING, sandstone), sand))),
				ifTrue(waterStartCheck(-6, -1), sequence(ifTrue(UNDER_FLOOR, sequence(ifTrue(ON_CEILING, sandstone), sand)), ifTrue(VERY_DEEP_UNDER_FLOOR, sandstone)))
		);
	}

	private static ConditionSource noiseRange(double low, double high) {
		return noiseCondition(Noises.SURFACE, low / 8.25D, high / 8.25D);
	}

	private static ConditionSource surfaceNoiseAbove(double noise) {
		return noiseCondition(Noises.SURFACE, noise / 8.25D, Double.MAX_VALUE);
	}

}
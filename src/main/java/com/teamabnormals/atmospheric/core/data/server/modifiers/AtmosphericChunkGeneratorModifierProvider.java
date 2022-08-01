package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.world.modification.chunk.ChunkGeneratorModifierProvider;
import com.teamabnormals.blueprint.common.world.modification.chunk.modifiers.SurfaceRuleModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public final class AtmosphericChunkGeneratorModifierProvider extends ChunkGeneratorModifierProvider {

	public AtmosphericChunkGeneratorModifierProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		ConditionSource inDunes = SurfaceRules.isBiome(AtmosphericBiomes.DUNES.getKey(), AtmosphericBiomes.FLOURISHING_DUNES.getKey(), AtmosphericBiomes.ROCKY_DUNES.getKey(), AtmosphericBiomes.PETRIFIED_DUNES.getKey());
		RuleSource aridSand = state(AtmosphericBlocks.ARID_SAND.get().defaultBlockState());
		RuleSource aridSandstone = state(AtmosphericBlocks.ARID_SANDSTONE.get().defaultBlockState());
		RuleSource redAridSand = state(AtmosphericBlocks.RED_ARID_SAND.get().defaultBlockState());
		RuleSource redAridSandstone = state(AtmosphericBlocks.RED_ARID_SANDSTONE.get().defaultBlockState());

		RuleSource aridSandRuleSource = SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), sequence(ifTrue(SurfaceRules.ON_CEILING, aridSandstone), aridSand))),
				SurfaceRules.ifTrue(waterStartCheck(-6, -1), sequence(SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, sequence(ifTrue(SurfaceRules.ON_CEILING, aridSandstone), aridSand)), SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, aridSandstone)))
		);

		RuleSource redAridSandRuleSource = SurfaceRules.sequence(
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ifTrue(waterBlockCheck(-1, 0), sequence(ifTrue(SurfaceRules.ON_CEILING, redAridSandstone), redAridSand))),
				SurfaceRules.ifTrue(waterStartCheck(-6, -1), sequence(SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, sequence(ifTrue(SurfaceRules.ON_CEILING, redAridSandstone), redAridSand)), SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, redAridSandstone)))
		);

		this.entry("dunes_surface_rule").selects("minecraft:overworld").addModifier(new SurfaceRuleModifier(SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), ifTrue(inDunes, sequence(ifTrue(noiseCondition(Noises.SURFACE, 0.3F / 8.25F, 2.5F / 8.25F), redAridSandRuleSource), aridSandRuleSource))), false));
	}

}
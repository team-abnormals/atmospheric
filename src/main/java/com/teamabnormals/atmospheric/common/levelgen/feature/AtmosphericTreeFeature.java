package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.HashMap;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class AtmosphericTreeFeature extends Feature<TreeConfiguration> {
	public Set<BlockPos> logPositions;
	public Set<BlockPos> foliagePositions;

	public HashMap<BlockPos, BlockState> specialLogPositions;
	public HashMap<BlockPos, BlockState> specialFoliagePositions;

	public AtmosphericTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		TreeConfiguration config = context.config();
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		this.logPositions = Sets.newHashSet();
		this.foliagePositions = Sets.newHashSet();
		this.specialLogPositions = Maps.newHashMap();
		this.specialFoliagePositions = Maps.newHashMap();

		if (TreeUtil.isValidGround(level, origin.below(), (SaplingBlock) this.getSapling())) {
			this.doPlace(context);

			for (BlockPos logPos : this.logPositions) {
				if (!TreeFeature.validTreePos(level, logPos) || logPos.getY() > level.getMaxBuildHeight()) return false;
			}

			for (BlockPos foliagePos : this.foliagePositions) {
				if (!TreeFeature.validTreePos(level, foliagePos) || foliagePos.getY() > level.getMaxBuildHeight()) return false;
			}

			setDirtAt(level, random, origin.below(), config);
			this.logPositions.forEach(logPos -> TreeUtil.setForcedState(level, logPos, this.specialLogPositions.getOrDefault(logPos, config.trunkProvider.getState(random, logPos))));
			this.foliagePositions.forEach(foliagePos -> {
				if (TreeFeature.validTreePos(level, foliagePos))
					TreeUtil.setForcedState(level, foliagePos, this.specialFoliagePositions.getOrDefault(foliagePos, config.foliageProvider.getState(random, foliagePos)));
			});

			TreeUtil.updateLeaves(level, this.logPositions);
			if (!config.decorators.isEmpty()) {
				BiConsumer<BlockPos, BlockState> decorationSetter = (decorationPos, state) -> level.setBlock(decorationPos, state, 19);
				TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, decorationSetter, random, this.logPositions, this.foliagePositions, Sets.newHashSet());
				config.decorators.forEach((decorator) -> decorator.place(decoratorContext));
			}

			return true;
		} else {
			return false;
		}
	}

	public abstract Block getSapling();

	public abstract void doPlace(FeaturePlaceContext<TreeConfiguration> context);

	public void addLog(BlockPos pos) {
		this.logPositions.add(pos.immutable());
	}

	public void addSpecialLog(BlockPos pos, BlockState state) {
		this.addLog(pos);
		this.specialLogPositions.put(pos, state);
	}

	public void addFoliage(BlockPos pos) {
		this.foliagePositions.add(pos.immutable());
	}

	public void addSpecialFoliage(BlockPos pos, BlockState state) {
		this.addFoliage(pos);
		this.specialFoliagePositions.put(pos.immutable(), state);
	}

	public static void setDirtAt(WorldGenLevel level, RandomSource random, BlockPos pos, TreeConfiguration config) {
		if (config.forceDirt || !isDirt(level, pos)) {
			TreeUtil.setForcedState(level, pos, config.dirtProvider.getState(random, pos));
		}
	}

	public static boolean isDirt(LevelSimulatedReader level, BlockPos pos) {
		return level.isStateAtPosition(pos, (state) -> Feature.isDirt(state) && !state.is(Blocks.GRASS_BLOCK) && !state.is(Blocks.MYCELIUM));
	}
}
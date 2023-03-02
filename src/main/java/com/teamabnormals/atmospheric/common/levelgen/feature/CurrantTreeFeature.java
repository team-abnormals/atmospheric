package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.Set;
import java.util.function.BiConsumer;

public class CurrantTreeFeature extends Feature<TreeConfiguration> {
	private Set<BlockPos> logPositions;
	private Set<BlockPos> foliagePositions;

	public CurrantTreeFeature(Codec<TreeConfiguration> config) {
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

		if (TreeUtil.isValidGround(level, origin.below(), (SaplingBlock) AtmosphericBlocks.CURRANT_SEEDLING.get())) {
			int trunkHeight = config.trunkPlacer.getTreeHeight(random);
			for (int y = 0; y < trunkHeight; y++) {
				this.addLog(origin.above(y));
			}

			int bottomSize = Math.max(5, 3 + random.nextInt(3) + random.nextInt(2));
			int topSize = Math.max(3, 2 + random.nextInt(2) + random.nextInt(2));

			this.createLeafLayer(origin.above(), bottomSize, random);
			this.createLeafLayer(origin.above(trunkHeight), topSize, random);

			for (BlockPos logPos : this.logPositions) {
				if (!TreeFeature.validTreePos(level, logPos) || logPos.getY() > level.getMaxBuildHeight()) return false;
			}

			for (BlockPos foliagePos : this.foliagePositions) {
				if (!TreeFeature.validTreePos(level, foliagePos) || foliagePos.getY() > level.getMaxBuildHeight()) return false;
			}

			LaurelTreeFeature.setDirtAt(level, random, origin.below(), config);
			this.logPositions.forEach(logPos -> TreeUtil.setForcedState(level, logPos, config.trunkProvider.getState(random, logPos)));
			this.foliagePositions.forEach(foliagePos -> {
				if (TreeFeature.validTreePos(level, foliagePos))
					TreeUtil.setForcedState(level, foliagePos, config.foliageProvider.getState(random, foliagePos));
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

	private void addLog(BlockPos pos) {
		this.logPositions.add(pos.immutable());
	}

	private void addFoliage(BlockPos pos) {
		this.foliagePositions.add(pos.immutable());
	}

	private void createLeafLayer(BlockPos pos, int leafSize, RandomSource random) {
		BlockPos offsetPos = pos.offset(-1 - random.nextInt(leafSize - 2), 0, -1 - random.nextInt(leafSize - 2));
		for (int i = 0; i < leafSize; i++) {
			for (int k = 0; k < leafSize; k++) {
				this.addFoliage(offsetPos.offset(i, 0, k));
			}
		}
	}
}
package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.function.Supplier;

import com.bagel.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.bagel.atmospheric.core.other.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.utils.TreeUtils;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class BabyYuccaTreeFeature extends Feature<BaseTreeFeatureConfig> {
	private boolean patch;
	private Supplier<BlockState> YUCCA_FLOWER = () -> AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();

	private Supplier<BlockState> TALL_YUCCA_FLOWER_TOP = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER);
	private Supplier<BlockState> TALL_YUCCA_FLOWER_BOTTOM = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER);
	
	public BabyYuccaTreeFeature(Codec<BaseTreeFeatureConfig> config, boolean patch) {
		super(config);
		this.patch = patch;
	}
	
	@Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
		int height = 2 + rand.nextInt(2) + rand.nextInt(2);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) k = 0;
				if (j >= position.getY() + 1 + height - 2) k = 2;
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getHeight()) {
							if (!TreeUtils.isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) flag = false;
						} else flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (TreeUtils.isInTag(worldIn, position.down(), AtmosphericTags.YUCCA_PLANTABLE_ON) && position.getY() < worldIn.getHeight()) {
				if (!TreeUtils.isInTag(worldIn, position.down(), BlockTags.SAND)) TreeUtils.setDirtAt(worldIn, position.down());
				
				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (TreeUtils.isAirOrLeaves(worldIn, blockpos)) {
					    TreeUtils.placeDirectionalLogAt(worldIn, blockpos, Direction.UP, rand, config);
					}
				}
				
				logY = position.getY() + height - 1;
				
				position = new BlockPos(logX, logY, logZ);

				this.createYuccaLeaves(worldIn, position.up(), rand, config, false);
				this.createYuccaLeaves(worldIn, position, rand, config, true);
				this.createYuccaLeaves(worldIn, position.down(), rand, config, false);

				if (patch) {
					for(int j = 0; j < 64; ++j) {
						BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
						if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && YUCCA_FLOWER.get().isValidPosition((IWorldReader)worldIn, blockpos)) {
				            placeFlowerAt(worldIn, blockpos, rand);
						}
					}
				}
				
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void createYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, BaseTreeFeatureConfig config, boolean square) {
		int leafSize = 1;
		for(int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for(int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (square) {
					this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
					} else if (rand.nextInt(4) == 0) { 
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config); 
					}
				}
			}
		}
	}

	private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
		TreeUtils.placeLeafAt(world, pos, rand, config);
		if (rand.nextInt(8) == 0) { 
			placeFlowerAt(world, pos.up(), rand);
		}
	}
	
	private void placeFlowerAt(IWorldGenerationReader world, BlockPos pos, Random rand) {
		if (TreeUtils.isAir(world, pos)) {
			if (!TreeUtils.isAir(world, pos.up())) {
			    TreeUtils.setForcedState(world, pos, YUCCA_FLOWER.get());
			} else if (rand.nextInt(4) == 0) {
			    TreeUtils.setForcedState(world, pos, TALL_YUCCA_FLOWER_BOTTOM.get());
			    TreeUtils.setForcedState(world, pos.up(), TALL_YUCCA_FLOWER_TOP.get());
			} else {
			    TreeUtils.setForcedState(world, pos, YUCCA_FLOWER.get());
			}
		}
	}
}
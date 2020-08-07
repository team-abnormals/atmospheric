package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.function.Supplier;

import com.minecraftabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.utils.TreeUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class YuccaTreeFeature extends Feature<BaseTreeFeatureConfig> {
	private boolean patch;
	private boolean petrified;
	
	private Supplier<BlockState> YUCCA_LOG = () -> AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	private Supplier<BlockState> YUCCA_LEAVES = () -> AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);
	private Supplier<BlockState> YUCCA_FLOWER = () -> AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();
	private Supplier<BlockState> YUCCA_BRANCH = () -> AtmosphericBlocks.YUCCA_BRANCH.get().getDefaultState();
	
	private Supplier<BlockState> TALL_YUCCA_FLOWER_TOP = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER);
	private Supplier<BlockState> TALL_YUCCA_FLOWER_BOTTOM = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER);
	
	public YuccaTreeFeature(Codec<BaseTreeFeatureConfig> config, boolean patch, boolean petrified) {
		super(config);
		this.patch = patch;
		this.petrified = petrified;
	}

	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {

		if (petrified) {			
			Supplier<BlockState> newBlock = () -> AtmosphericBlocks.ARID_SANDSTONE.get().getDefaultState();
			Supplier<BlockState> newBlockWall = () -> AtmosphericBlocks.ARID_SANDSTONE_WALL.get().getDefaultState();
			
			if (rand.nextInt(4) == 0) {
				newBlock = () -> AtmosphericBlocks.RED_ARID_SANDSTONE.get().getDefaultState();
				newBlockWall = () -> AtmosphericBlocks.RED_ARID_SANDSTONE_WALL.get().getDefaultState();
			}
			
			YUCCA_LOG = newBlock;
			YUCCA_LEAVES = newBlock;
			YUCCA_FLOWER = newBlockWall;
			YUCCA_BRANCH = newBlockWall;
		}
		
		int height = 4 + rand.nextInt(2) + rand.nextInt(2);
		int reduction = 2 + rand.nextInt(3);
		if (this.petrified) height -= reduction;
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
				//base log
				if (!TreeUtils.isInTag(worldIn, position.down(), BlockTags.SAND)) TreeUtils.setDirtAt(worldIn, position.down());
				for (int ja = 0; ja < reduction; ++ja) {
					if (this.petrified) this.placeLogAt(worldIn, position.down(ja), Direction.UP, false);
				}
				
				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (TreeUtils.isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(worldIn, blockpos, Direction.UP, false);
					}
				}
				
				logY = position.getY() + height - 1;
								
				BlockPos newPos = this.createYuccaBranch(height, worldIn, position, Direction.NORTH, rand, config);
				this.createYuccaLeaves(worldIn, newPos.up(), rand, false);
				this.createYuccaLeaves(worldIn, newPos, rand, true);
				this.createYuccaLeaves(worldIn, newPos.down(), rand, false);

				newPos = this.createYuccaBranch(height, worldIn, position, Direction.EAST, rand, config);
				this.createYuccaLeaves(worldIn, newPos.up(), rand, false);
				this.createYuccaLeaves(worldIn, newPos, rand, true);
				this.createYuccaLeaves(worldIn, newPos.down(), rand, false);

				newPos = this.createYuccaBranch(height, worldIn, position, Direction.SOUTH, rand, config);
				this.createYuccaLeaves(worldIn, newPos.up(), rand, false);
				this.createYuccaLeaves(worldIn, newPos, rand, true);
				this.createYuccaLeaves(worldIn, newPos.down(), rand, false);

				newPos = this.createYuccaBranch(height, worldIn, position, Direction.WEST, rand, config);
				this.createYuccaLeaves(worldIn, newPos.up(), rand, false);
				this.createYuccaLeaves(worldIn, newPos, rand, true);
				this.createYuccaLeaves(worldIn, newPos.down(), rand, false);

				if (patch) {
					for(int j = 0; j < 64; ++j) {
						BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
						if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && YUCCA_FLOWER.get().isValidPosition((IWorldReader)worldIn, blockpos)) {
				            placeFlowerAt(worldIn, blockpos, rand);
						}
					}
				}
				
				if (petrified && rand.nextInt(12) == 0) {
					for(int j = 0; j < 12; ++j) {
						BlockPos blockpos = position.add(rand.nextInt(6) - rand.nextInt(6), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(6) - rand.nextInt(6));
						if (TreeUtils.isAir(worldIn, blockpos) && blockpos.getY() < 255 && TreeUtils.isInTag(worldIn, blockpos.down(), BlockTags.SAND)) {
						    TreeUtils.setForcedState(worldIn, blockpos, AtmosphericBlocks.ROASTED_YUCCA_BUNDLE.get().getDefaultState());
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
	
	private void createYuccaLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, boolean square) {
		int leafSize = 1;
		for(int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for(int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (square) {
					this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand);
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand);
					} else if (rand.nextInt(4) == 0) { 
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand); 
					}
				}
			}
		}
	}
	
	private BlockPos createYuccaBranch(int height, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, Random rand, BaseTreeFeatureConfig config) {
		int logX = pos.getX();
		int logZ = pos.getZ();
		int logY = pos.getY() + height - 1;
		int length = 4 + rand.nextInt(2);
		BlockPos blockpos = new BlockPos(logX, logY, logZ);
		boolean bundle = false;
		boolean anyBundle = false;

		for (int i = 0; i < length; i++) {
			blockpos = new BlockPos(logX, logY, logZ);
			if (!anyBundle && rand.nextInt(16) == 0) {
				bundle = true; 
				anyBundle = true;
			} else {
				bundle = false;
			}
			this.createHorizontalLog(1, worldIn, blockpos, direction, bundle);
			if (i != length) {
				if (direction == Direction.EAST || direction == Direction.WEST) {
					if (direction == Direction.EAST) { logX += rand.nextInt(2);
					} else { logX -= rand.nextInt(2); }
				} else {
					if (direction == Direction.SOUTH) { logZ += rand.nextInt(2);
					} else { logZ -= rand.nextInt(2); }
				}
				logY += 1;
			}
		}
		return blockpos.offset(direction);
	}
	
	private void createHorizontalLog(int branchLength, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, boolean bundle) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();
		
		for (int k3 = 0; k3 < branchLength; ++k3) {
			logX += direction.getXOffset();
			logZ += direction.getZOffset();
			BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
			if (TreeUtils.isAirOrLeaves(worldIn, blockpos1)) {
				if (!TreeUtils.isAir(worldIn, blockpos1.down())) bundle = false;
				this.placeLogAt(worldIn, blockpos1, Direction.UP, bundle);
			}
		}
	}

	private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Direction direction, boolean bundle) {
		BlockState logState = this.petrified ? YUCCA_LOG.get() : YUCCA_LOG.get().with(RotatedPillarBlock.AXIS, direction.getAxis());
		TreeUtils.setForcedState(worldIn, pos, logState);
		if (bundle && !this.petrified) {
		    TreeUtils.setForcedState(worldIn, pos.down(), YUCCA_BRANCH.get());
		}
	}

	private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand) {
		if (TreeUtils.isAirOrLeaves(world, pos) && !this.petrified) { 
		    TreeUtils.setForcedState(world, pos, YUCCA_LEAVES.get());
		}
		if (rand.nextInt(8) == 0 && !this.petrified) { 
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
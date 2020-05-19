package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import com.bagel.atmospheric.core.other.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

@SuppressWarnings("unused")
public class YuccaTreeFeature extends TreeFeature {
	private final Supplier<BlockState> YUCCA_LOG = () -> AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	private final Supplier<BlockState> YUCCA_LEAVES = () -> AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);
	private final Supplier<BlockState> YUCCA_FLOWER = () -> AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();
	
	public YuccaTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i51443_1_, boolean p_i51443_2_) {
		super(p_i51443_1_);
	}

	public boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logsPlaced, Set<BlockPos> leavesPlaced, MutableBoundingBox boundsIn, TreeFeatureConfig config) {
		int height = 4 + rand.nextInt(2) + rand.nextInt(2);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) k = 0;
				if (j >= position.getY() + 1 + height - 2) k = 2;
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxHeight()) {
							if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) flag = false;
						} else flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (isYucca(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight()) {
				//base log
				if (!isSand(worldIn, position.down())) this.setDirtAt(worldIn, position.down(), position);
				Direction direction = Direction.Plane.HORIZONTAL.random(rand);

				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				BlockPos sapling = position;

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(logsPlaced, worldIn, blockpos, boundsIn, Direction.UP);
					}
				}
				
				logY = position.getY() + height - 1;
				

				
				BlockPos newPos = this.createYuccaBranch(height, logsPlaced, worldIn, position, Direction.NORTH, boundsIn, rand);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.up(), boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos, boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.down(), boundsIn);

				newPos = this.createYuccaBranch(height, logsPlaced, worldIn, position, Direction.EAST, boundsIn, rand);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.up(), boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos, boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.down(), boundsIn);

				newPos = this.createYuccaBranch(height, logsPlaced, worldIn, position, Direction.SOUTH, boundsIn, rand);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.up(), boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos, boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.down(), boundsIn);

				newPos = this.createYuccaBranch(height, logsPlaced, worldIn, position, Direction.WEST, boundsIn, rand);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.up(), boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos, boundsIn);
				this.createYuccaLeaves(leavesPlaced, worldIn, newPos.down(), boundsIn);

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void createYuccaLeaves(Set<BlockPos> leavesPlaced, IWorldGenerationReader worldIn, BlockPos newPos, MutableBoundingBox boundsIn) {
		Random rand = new Random();
		int leafSize = 1;
		for(int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for(int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize) && rand.nextBoolean()) {
					this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn);
				}
			}
		}
	}
	
	private BlockPos createYuccaBranch(int height, Set<BlockPos> logsPlaced, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, MutableBoundingBox boundsIn, Random rand) {
		int logX = pos.getX();
		int logZ = pos.getZ();
		int logY = pos.getY() + height - 1;
		int length = 4 + rand.nextInt(2);
		BlockPos blockpos = new BlockPos(logX, logY, logZ);

		for (int i = 0; i < length; i++) {
			blockpos = new BlockPos(logX, logY, logZ);
			this.createHorizontalLog(1, logsPlaced, worldIn, blockpos, direction, boundsIn);
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
	
	private void createHorizontalLog(int branchLength, Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos pos, Direction direction, MutableBoundingBox boundsIn) {
		int logX = pos.getX();
		int logY = pos.getY();
		int logZ = pos.getZ();
		
		for (int k3 = 0; k3 < branchLength; ++k3) {
			logX += direction.getXOffset();
			logZ += direction.getZOffset();
			BlockPos blockpos1 = new BlockPos(logX, logY, logZ);
			if (isAirOrLeaves(worldIn, blockpos1)) {
				this.placeLogAt(changedBlocks, worldIn, blockpos1, boundsIn, Direction.UP);
			}
		}
	}

	private void placeLogAt(Set<BlockPos> changedBlocks, IWorldWriter worldIn, BlockPos pos, MutableBoundingBox boundsIn, Direction direction) {
		this.setLogState(changedBlocks, worldIn, pos, YUCCA_LOG.get().with(LogBlock.AXIS, direction.getAxis()), boundsIn);
	}

	private void placeLeafAt(Set<BlockPos> changedBlocks, IWorldGenerationReader world, BlockPos pos, MutableBoundingBox boundsIn) {
		if (isAirOrLeaves(world, pos)) { 
			this.setLogState(changedBlocks, world, pos, YUCCA_LEAVES.get(), boundsIn);
		}
	}
	
	protected final void setLogState(Set<BlockPos> changedBlocks, IWorldWriter worldIn, BlockPos pos, BlockState state, MutableBoundingBox boundsIn) {
		worldIn.setBlockState(pos, state, 18);
		boundsIn.expandTo(new MutableBoundingBox(pos, pos));
		if (BlockTags.LOGS.contains(state.getBlock())) {
			changedBlocks.add(pos.toImmutable());
		}
	}
	
	protected static boolean isYucca(IWorldGenerationBaseReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
		return reader.hasBlockState(pos, state -> state.getBlock().isIn(AtmosphericTags.YUCCA_PLANTABLE_ON));
	}
	
	public static boolean isSand(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		return worldIn.hasBlockState(pos, (block) -> {
			return block.isIn(BlockTags.SAND);
		});
	}
}
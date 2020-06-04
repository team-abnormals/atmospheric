package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import com.bagel.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.bagel.atmospheric.core.other.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class BabyYuccaTreeFeature extends TreeFeature {
	private boolean patch;
	
	private Supplier<BlockState> YUCCA_LOG = () -> AtmosphericBlocks.YUCCA_LOG.get().getDefaultState();
	private Supplier<BlockState> YUCCA_LEAVES = () -> AtmosphericBlocks.YUCCA_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1);
	private Supplier<BlockState> YUCCA_FLOWER = () -> AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();

	private Supplier<BlockState> TALL_YUCCA_FLOWER_TOP = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER);
	private Supplier<BlockState> TALL_YUCCA_FLOWER_BOTTOM = () -> AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER);
	
	public BabyYuccaTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> config, boolean patch) {
		super(config);
		this.patch = patch;
	}

	public boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logsPlaced, Set<BlockPos> leavesPlaced, MutableBoundingBox boundsIn, TreeFeatureConfig config) {
		
		int height = 2 + rand.nextInt(2) + rand.nextInt(2);
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
				if (!isSand(worldIn, position.down())) this.setDirtAt(worldIn, position.down(), position);
				
				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(logsPlaced, worldIn, blockpos, boundsIn, Direction.UP);
					}
				}
				
				logY = position.getY() + height - 1;
				
				position = new BlockPos(logX, logY, logZ);

				this.createYuccaLeaves(leavesPlaced, worldIn, position.up(), boundsIn, rand, false);
				this.createYuccaLeaves(leavesPlaced, worldIn, position, boundsIn, rand, true);
				this.createYuccaLeaves(leavesPlaced, worldIn, position.down(), boundsIn, rand, false);

				if (patch) {
					for(int j = 0; j < 64; ++j) {
						BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
						if (isAir(worldIn, blockpos) && blockpos.getY() < 255 && YUCCA_FLOWER.get().isValidPosition((IWorldReader)worldIn, blockpos)) {
				            placeFlowerAt(leavesPlaced, worldIn, blockpos, boundsIn, rand);
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
	
	private void createYuccaLeaves(Set<BlockPos> leavesPlaced, IWorldGenerationReader worldIn, BlockPos newPos, MutableBoundingBox boundsIn, Random rand, boolean square) {
		int leafSize = 1;
		for(int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for(int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (square) {
					this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand);
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand);
					} else if (rand.nextInt(4) == 0) { 
						this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand); 
					}
				}
			}
		}
	}

	

	private void placeLogAt(Set<BlockPos> changedBlocks, IWorldWriter worldIn, BlockPos pos, MutableBoundingBox boundsIn, Direction direction) {
		this.setLogState(changedBlocks, worldIn, pos, YUCCA_LOG.get().with(LogBlock.AXIS, direction.getAxis()), boundsIn);
	}

	private void placeLeafAt(Set<BlockPos> changedBlocks, IWorldGenerationReader world, BlockPos pos, MutableBoundingBox boundsIn, Random rand) {
		if (isAirOrLeaves(world, pos)) { 
			this.setLogState(changedBlocks, world, pos, YUCCA_LEAVES.get(), boundsIn);
		}
		if (rand.nextInt(8) == 0) { 
			placeFlowerAt(changedBlocks, world, pos.up(), boundsIn, rand);
		}
	}
	
	private void placeFlowerAt(Set<BlockPos> changedBlocks, IWorldGenerationReader world, BlockPos pos, MutableBoundingBox boundsIn, Random rand) {
		if (isAir(world, pos)) {
			if (!isAir(world, pos.up())) {
				this.setLogState(changedBlocks, world, pos, YUCCA_FLOWER.get(), boundsIn);
			} else if (rand.nextInt(4) == 0) {
				this.setLogState(changedBlocks, world, pos, TALL_YUCCA_FLOWER_BOTTOM.get(), boundsIn);
				this.setLogState(changedBlocks, world, pos.up(), TALL_YUCCA_FLOWER_TOP.get(), boundsIn);
			} else {
				this.setLogState(changedBlocks, world, pos, YUCCA_FLOWER.get(), boundsIn);
			}
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
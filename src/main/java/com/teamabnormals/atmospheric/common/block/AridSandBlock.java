package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;


public class AridSandBlock extends FallingBlock implements BonemealableBlock {
	private final int color;

	public AridSandBlock(int color, Properties properties) {
		super(properties);
		this.color = color;
	}

	@Override
	public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
		return this.color;
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction facing, IPlantable plantable) {
		BlockState plant = plantable.getPlant(level, pos.relative(facing));
		PlantType type = plantable.getPlantType(level, pos.relative(facing));

		if (plant.getBlock() == Blocks.CACTUS)
			return true;
		if (PlantType.DESERT.equals(type)) {
			return true;
		} else if (PlantType.BEACH.equals(type)) {
			boolean hasWater = false;
			for (Direction face : Direction.Plane.HORIZONTAL) {
				BlockState blockState = level.getBlockState(pos.relative(face));
				FluidState fluidState = level.getFluidState(pos.relative(face));
				hasWater |= blockState.is(Blocks.FROSTED_ICE);
				hasWater |= fluidState.is(FluidTags.WATER);
				if (hasWater) {
					return true;
				}
			}
		}
		return super.canSustainPlant(state, level, pos, facing, plantable);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.above()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = AtmosphericBlocks.ARID_SPROUTS.get().defaultBlockState();

		label48:
		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos1 = blockpos;

			for (int j = 0; j < i / 16; ++j) {
				blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
				if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1)) {
					continue label48;
				}
			}

			BlockState blockstate2 = worldIn.getBlockState(blockpos1);
			if (blockstate2.isAir()) {
				BlockState blockstate1;
				if (rand.nextInt(8) == 0) {
					Holder<Biome> biome = worldIn.getBiome(blockpos1);
					if (biome.is(AtmosphericBiomes.FLOURISHING_DUNES.getKey()))
						blockstate1 = AtmosphericBlocks.GILIA.get().defaultBlockState();
					else {
						blockstate1 = AtmosphericBlocks.YUCCA_FLOWER.get().defaultBlockState();
					}
				} else {
					blockstate1 = blockstate;
				}

				if (blockstate1.canSurvive(worldIn, blockpos1)) {
					worldIn.setBlock(blockpos1, blockstate1, 3);
					if (blockstate1.is(AtmosphericBlocks.YUCCA_FLOWER.get()) && rand.nextInt(10) == 0) {
						((BonemealableBlock) blockstate1.getBlock()).performBonemeal(worldIn, rand, blockpos1, blockstate1);
					}
				}
			}
		}

	}
}
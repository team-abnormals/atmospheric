package com.teamabnormals.atmospheric.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;


public class AridSandBlock extends FallingBlock implements BonemealableBlock {
	private final int color;

	public AridSandBlock(int color, Properties properties) {
		super(properties);
		this.color = color;
	}

	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return null;
	}

	@Override
	public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
		return this.color;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
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
					if (biome.is(AtmosphericBiomes.FLOURISHING_DUNES))
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
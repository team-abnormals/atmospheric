package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class CoarseDirtPatchFeature extends Feature<SphereReplaceConfig> {
    public CoarseDirtPatchFeature(Codec<SphereReplaceConfig> p_i49885_1_) {
        super(p_i49885_1_);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, SphereReplaceConfig config) {
        if (worldIn.getFluidState(pos).isTagged(FluidTags.WATER)) {
            return false;
        } else {
            int i = 0;
            int j = rand.nextInt(config.radius - 2) + 2;

            for (int k = pos.getX() - j; k <= pos.getX() + j; ++k) {
                for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l) {
                    int i1 = k - pos.getX();
                    int j1 = l - pos.getZ();
                    if (i1 * i1 + j1 * j1 <= j * j) {
                        for (int k1 = pos.getY() - config.ySize; k1 <= pos.getY() + config.ySize; ++k1) {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            BlockState blockstate = worldIn.getBlockState(blockpos);

                            for (BlockState blockstate1 : config.targets) {
                                if (blockstate1.getBlock() == blockstate.getBlock()) {
                                    worldIn.setBlockState(blockpos, config.state, 2);
                                    if (worldIn.isAirBlock(blockpos.up())) {
                                        if (rand.nextInt(2) == 0) {
                                            if (worldIn.isAirBlock(blockpos.up(2)) && rand.nextInt(4) != 0) {
                                                worldIn.setBlockState(blockpos.up(), Blocks.TALL_GRASS.getDefaultState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 2);
                                                worldIn.setBlockState(blockpos.up(2), Blocks.TALL_GRASS.getDefaultState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 2);
                                            } else {
                                                worldIn.setBlockState(blockpos.up(), Blocks.GRASS.getDefaultState(), 2);
                                            }
                                        }
                                    }
                                    ++i;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return i > 0;
        }
    }
}

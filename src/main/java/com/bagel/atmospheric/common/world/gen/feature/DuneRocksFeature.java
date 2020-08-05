package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class DuneRocksFeature extends Feature<BlockBlobConfig> {
    public DuneRocksFeature(Codec<BlockBlobConfig> p_i49915_1_) {
        super(p_i49915_1_);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, BlockBlobConfig config) {
        while (true) {
            label50: {
                if (pos.getY() > 3) {
                    if (worldIn.isAirBlock(pos.down())) {
                        break label50;
                    }

                    Block block = worldIn.getBlockState(pos.down()).getBlock();
                    if (block != AtmosphericBlocks.ARID_SAND.get() && block != AtmosphericBlocks.RED_ARID_SAND.get()) {
                        break label50;
                    }
                }

                if (pos.getY() <= 3) {
                    return false;
                }

                int i1 = config.startRadius;

                for (int i = 0; i1 >= 0 && i < 3; ++i) {
                    int j = i1 + rand.nextInt(2);
                    int k = i1 + rand.nextInt(2);
                    int l = i1 + rand.nextInt(2);
                    float f = (float) (j + k + l) * 0.333F + 0.5F;

                    for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-j, -k, -l), pos.add(j, k, l))) {
                        if (blockpos.distanceSq(pos) <= (double) (f * f)) {
                            worldIn.setBlockState(blockpos, rand.nextBoolean() ? AtmosphericBlocks.RED_ARID_SANDSTONE.get().getDefaultState() : AtmosphericBlocks.ARID_SANDSTONE.get().getDefaultState(), 4);
                        }
                    }

                    pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
                }

                return true;
            }

            pos = pos.down();
        }
    }
}

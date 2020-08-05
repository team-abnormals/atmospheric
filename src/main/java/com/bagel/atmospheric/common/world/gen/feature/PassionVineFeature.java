package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.bagel.atmospheric.common.block.PassionVineBlock;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class PassionVineFeature extends Feature<NoFeatureConfig> {
    public PassionVineFeature(Codec<NoFeatureConfig> p_i49876_1_) {
        super(p_i49876_1_);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        for (int j = 0; j < 400; ++j) {

            Direction direction = Direction.Plane.HORIZONTAL.random(rand);
            BlockState blockstate = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction).with(PassionVineBlock.AGE, rand.nextInt(2));
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            int lengthA = 3 + rand.nextInt(2) + rand.nextInt(2) + rand.nextInt(2) - rand.nextInt(3);

            if (worldIn.isAirBlock(blockpos) && blockpos.getY() > 50 && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, blockstate, 6);
                for (int length = 0; length < lengthA; ++length) {
                    blockpos = blockpos.down();
                    if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
                        worldIn.setBlockState(blockpos, blockstate.with(PassionVineBlock.AGE, rand.nextInt(2)), 6);
                    } else {
                        break;
                    }
                }
                ++i;
            }
        }
        return i > 0;

    }
}

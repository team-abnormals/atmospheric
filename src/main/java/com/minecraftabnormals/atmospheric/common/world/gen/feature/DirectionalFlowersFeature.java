package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.atmospheric.common.block.MonkeyBrushBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class DirectionalFlowersFeature extends Feature<NoFeatureConfig> {
    int temp;

    public DirectionalFlowersFeature(Codec<NoFeatureConfig> p_i49876_1_, int temperature) {
        super(p_i49876_1_);
        temp = temperature;
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockState blockstate = AtmosphericBlocks.HOT_MONKEY_BRUSH.get().getDefaultState();
        if (temp == 3) {
            blockstate = AtmosphericBlocks.SCALDING_MONKEY_BRUSH.get().getDefaultState();
        } else if (temp == 1) {
            blockstate = AtmosphericBlocks.WARM_MONKEY_BRUSH.get().getDefaultState();
        }
        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
                Direction randomD = Direction.func_239631_a_(rand);
                while (randomD == Direction.DOWN || !blockstate.with(MonkeyBrushBlock.FACING, randomD).isValidPosition(worldIn, blockpos)) {
                    randomD = Direction.func_239631_a_(rand);
                }
                BlockState blockstate2 = blockstate.with(MonkeyBrushBlock.FACING, randomD);
                worldIn.setBlockState(blockpos, blockstate2, 2);
                ++i;
            }
        }

        return i > 0;
    }
}

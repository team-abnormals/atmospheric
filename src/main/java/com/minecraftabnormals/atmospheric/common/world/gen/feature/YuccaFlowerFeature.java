package com.minecraftabnormals.atmospheric.common.world.gen.feature;

import com.minecraftabnormals.atmospheric.common.block.YuccaFlowerDoubleBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;

import java.util.Random;

public class YuccaFlowerFeature extends DefaultFlowersFeature {
    public YuccaFlowerFeature(Codec<BlockClusterFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
        BlockState blockstate = AtmosphericBlocks.YUCCA_FLOWER.get().getDefaultState();
        BlockState tallBlockstate = AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState();
        int i = 0;

        for (int j = 0; j < this.getFlowerCount(config); ++j) {
            BlockPos blockpos = this.getNearbyPos(rand, pos, config);
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getHeight() - 1 && blockstate.isValidPosition(worldIn, blockpos) && this.isValidPosition(worldIn, blockpos, config)) {
                if (rand.nextBoolean() && worldIn.isAirBlock(blockpos.up())) {
                    worldIn.setBlockState(blockpos, tallBlockstate, 2);
                    worldIn.setBlockState(blockpos.up(), tallBlockstate.with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), 2);
                } else {
                    worldIn.setBlockState(blockpos, blockstate, 2);
                }
                ++i;
            }
        }
        return i > 0;
    }
}

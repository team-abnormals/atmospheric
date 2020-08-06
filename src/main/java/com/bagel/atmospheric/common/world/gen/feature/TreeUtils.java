package com.bagel.atmospheric.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraftforge.common.IPlantable;

public class TreeUtils {
    
    public static void placeLogAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
        setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos));
    }
    
    public static void placeDirectionalLogAt(IWorldWriter worldIn, BlockPos pos, Direction direction, Random rand, BaseTreeFeatureConfig config) {
        setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos).with(RotatedPillarBlock.AXIS, direction.getAxis()));
    }
    
    public static boolean isInTag(IWorldGenerationBaseReader worldIn, BlockPos pos, INamedTag<Block> tag) {
        return worldIn.hasBlockState(pos, (block) -> {
            return block.isIn(tag);
        });
    }

    public static void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
        if (isAirOrLeaves(world, pos)) {
            setLogState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
        }
    }

    public static void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }

    @SuppressWarnings("deprecation")
    public static boolean isAir(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (!(worldIn instanceof net.minecraft.world.IBlockReader)) {
            return worldIn.hasBlockState(pos, BlockState::isAir);
        } else {
            return worldIn.hasBlockState(pos, state -> state.isAir((net.minecraft.world.IBlockReader) worldIn, pos));
        }
    }

    public static boolean isLog(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isIn(BlockTags.LOGS);
        });
    }

    public static boolean isLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isIn(BlockTags.LEAVES);
        });
    }

    @SuppressWarnings("deprecation")
    public static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (worldIn instanceof net.minecraft.world.IWorldReader) {
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
        }
        return worldIn.hasBlockState(pos, (state) -> {
            return state.isAir() || state.isIn(BlockTags.LEAVES);
        });
    }

    public static void setDirtAt(IWorld worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND) {
            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 18);
        }
    }

    public static boolean isValidGround(IWorld world, BlockPos pos, SaplingBlock sapling) {
        return world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (IPlantable) sapling);
    }
}
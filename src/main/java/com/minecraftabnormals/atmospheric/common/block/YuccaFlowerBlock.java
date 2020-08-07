package com.minecraftabnormals.atmospheric.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;
import com.minecraftabnormals.atmospheric.common.block.api.IYuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class YuccaFlowerBlock extends FlowerBlock implements IGrowable, IYuccaPlant {
	private final Supplier<Effect> stewEffect;
	
	public YuccaFlowerBlock(Supplier<Effect> effect, int effectDuration, Properties properties) {
		super(Effects.BLINDNESS, effectDuration, properties);
		this.stewEffect = effect;
	}
	
	public Effect getStewEffect() {
		return this.stewEffect.get();
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.down());
		return state2.isIn(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
	}
	
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		if(AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().isValidPosition(world, pos) && (world.isAirBlock(pos.up()))) {
			this.placeAt(world, pos, 2);
		}
	}
	
	@Override
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlockState(pos, AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlockState(pos.up(), AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }

    @Override
    public float getKnockbackForce() {
        return 0.5F;
    }

    @Override
    public DamageSource getDamageSource() {
        return AtmosphericDamageSources.YUCCA_FLOWER;
    }

    @Override
    public EmptyTrigger getCriteriaTrigger() {
        return AtmosphericCriteriaTriggers.YUCCA_FLOWER_PRICK;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        this.onYuccaCollision(state, worldIn, pos, entityIn);
    };
}

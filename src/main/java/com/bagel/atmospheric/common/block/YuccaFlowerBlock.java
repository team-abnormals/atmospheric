package com.bagel.atmospheric.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.atmospheric.core.other.AtmosphericDamageSources;
import com.bagel.atmospheric.core.other.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;
import com.google.common.base.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class YuccaFlowerBlock extends FlowerBlock implements IGrowable {
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
	
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlockState(pos, AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlockState(pos.up(), AtmosphericBlocks.TALL_YUCCA_FLOWER.get().getDefaultState().with(YuccaFlowerDoubleBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
				double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	if (!entityIn.isCrouching()) {
	            		entityIn.addVelocity(MathHelper.sin((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.075F, 0.025F, -MathHelper.cos((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.075F);
	            	}
	            	entityIn.attackEntityFrom(AtmosphericDamageSources.YUCCA_FLOWER, 1.0F);	
	            }
			}
		}	
	}
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }
}

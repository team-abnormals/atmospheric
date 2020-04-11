package com.bagel.atmospheric.common.block;

import javax.annotation.Nullable;

import com.bagel.atmospheric.core.data.AtmosphericDamageSources;
import com.bagel.atmospheric.core.data.AtmosphericTags;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class YuccaFlowerDoubleBlock extends DoublePlantBlock {

	public YuccaFlowerDoubleBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.down());
		if (state.get(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
			return state2.isIn(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.TALL_YUCCA_FLOWER.get() || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
		}
		return state2.isIn(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.getPosX() || entityIn.lastTickPosZ != entityIn.getPosZ())) {
				double d0 = Math.abs(entityIn.getPosX() - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.getPosZ() - entityIn.lastTickPosZ);
	            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
	            	if (!entityIn.isCrouching()) {
	            		entityIn.addVelocity(MathHelper.sin((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.1F, 0.05F, -MathHelper.cos((float) (entityIn.rotationYaw * Math.PI / 180.0F)) * 2F * 0.1F);
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

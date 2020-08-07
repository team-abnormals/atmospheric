package com.minecraftabnormals.atmospheric.common.block;

import javax.annotation.Nullable;

import com.minecraftabnormals.atmospheric.common.block.api.IYuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.teamabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class YuccaLeavesBlock extends AbnormalsLeavesBlock implements IYuccaPlant {
    private static final VoxelShape HITBOX = makeCuboidShape(1.0F, 1.0F, 1.0F, 15.0F, 15.0F, 15.0F);

	public YuccaLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return HITBOX;
	}
	
	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}
	
	@Deprecated
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 0.2F;
	}
	
	@Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return  PathNodeType.DAMAGE_CACTUS;
    }

    @Override
    public float getKnockbackForce() {
        return 0.35F;
    }

    @Override
    public DamageSource getDamageSource() {
        return AtmosphericDamageSources.YUCCA_LEAVES;
    }

    @Override
    public EmptyTrigger getCriteriaTrigger() {
        return AtmosphericCriteriaTriggers.YUCCA_LEAVES_PRICK;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        this.onYuccaCollision(state, worldIn, pos, entityIn);
    };
}

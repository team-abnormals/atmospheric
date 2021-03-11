package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.minecraftabnormals.abnormals_core.common.blocks.LeafCarpetBlock;
import com.minecraftabnormals.atmospheric.common.block.api.IYuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class YuccaLeafCarpetBlock extends LeafCarpetBlock implements IYuccaPlant {

	public YuccaLeafCarpetBlock(Block.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_CACTUS;
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
	}
}

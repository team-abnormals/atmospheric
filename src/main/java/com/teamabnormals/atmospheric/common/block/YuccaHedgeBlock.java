package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import com.teamabnormals.blueprint.common.block.HedgeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;

public class YuccaHedgeBlock extends HedgeBlock implements YuccaPlant {

	public YuccaHedgeBlock(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DAMAGE_CACTUS;
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
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}

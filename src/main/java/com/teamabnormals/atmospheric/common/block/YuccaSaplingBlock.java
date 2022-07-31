package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.other.AtmosphericTags;
import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import com.teamabnormals.blueprint.common.block.wood.BlueprintSaplingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;

public class YuccaSaplingBlock extends BlueprintSaplingBlock implements IPlantable, YuccaPlant {
	public YuccaSaplingBlock(AbstractTreeGrower tree, Properties properties) {
		super(tree, properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.below()).is(AtmosphericTags.YUCCA_PLANTABLE_ON);
	}

	@Override
	public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return net.minecraftforge.common.PlantType.DESERT;
	}

	@Override
	public float getKnockbackForce() {
		return 0.35F;
	}

	@Override
	public DamageSource getDamageSource() {
		return AtmosphericDamageSources.YUCCA_SAPLING;
	}

	@Override
	public EmptyTrigger getCriteriaTrigger() {
		return null;
	}

	@Nullable
	@Override
	public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DAMAGE_CACTUS;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}

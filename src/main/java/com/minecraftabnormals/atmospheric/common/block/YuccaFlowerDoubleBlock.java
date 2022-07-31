package com.minecraftabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.advancement.EmptyTrigger;
import com.teamabnormals.blueprint.common.block.BlueprintTallFlowerBlock;
import com.minecraftabnormals.atmospheric.common.block.api.YuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class YuccaFlowerDoubleBlock extends BlueprintTallFlowerBlock implements YuccaPlant {
	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public YuccaFlowerDoubleBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState state2 = worldIn.getBlockState(pos.below());
		if (state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER) {
			return state2.is(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.TALL_YUCCA_FLOWER.get() || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
		}
		return state2.is(AtmosphericTags.YUCCA_PLANTABLE_ON) || state2.getBlock() == AtmosphericBlocks.YUCCA_LEAVES.get() || state2.getBlock() == Blocks.CACTUS;
	}

	@Nullable
	@Override
	public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DAMAGE_CACTUS;
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(worldIn, pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public float getKnockbackForce() {
		return 0.65F;
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
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		this.onYuccaCollision(state, worldIn, pos, entityIn);
	}
}

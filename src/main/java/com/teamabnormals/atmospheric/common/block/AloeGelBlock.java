package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AloeGelBlock extends HalfTransparentBlock {
	public static final BooleanProperty WET = BooleanProperty.create("wet");

	public AloeGelBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WET, false));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return state.getValue(WET) ? Shapes.block() : Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(WET, this.touchingWater(context.getClickedPos(), context.getLevel()));
	}


	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WET);
	}

	@Override
	public boolean isStickyBlock(BlockState state) {
		return !state.getValue(WET);
	}

	@Override
	public boolean canStickTo(BlockState state, BlockState other) {
		return (other.is(this) || !other.is(BlueprintBlockTags.ATTACHES_BLOCKS_TO_PISTONS)) && super.canStickTo(state, other);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.setValue(WET, this.touchingWater(currentPos, worldIn));
	}

	public final boolean touchingWater(BlockPos blockPos, BlockGetter iBlockReader) {
		for (Direction direction : Direction.values()) {
			if (iBlockReader.getFluidState(blockPos.relative(direction)).is(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return PathType.DAMAGE_OTHER;
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		if (!state.getValue(WET)) {
			entity.makeStuckInBlock(state, new Vec3(0.25D, 0.25D, 0.25D));
			if (entity instanceof LivingEntity living) {
				living.addEffect(new MobEffectInstance(MobEffects.POISON, 150, 0, false, true, true));
				living.clearFire();
			}
		}
	}
}

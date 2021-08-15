package com.minecraftabnormals.atmospheric.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class AloeGelBlock extends BreakableBlock {

	public static final BooleanProperty WET = BooleanProperty.create("wet");

	public AloeGelBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WET, Boolean.valueOf(false)));
	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.getValue(WET) ? VoxelShapes.block() : VoxelShapes.empty();
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(WET, Boolean.valueOf(this.touchingWater(context.getClickedPos(), context.getLevel())));
	}


	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WET);
	}

	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	public boolean isStickyBlock(BlockState state) {
		return !state.getValue(WET);
	}

	@Override
	public boolean isSlimeBlock(BlockState state) {
		return !state.getValue(WET);
	}

	public boolean canStickTo(BlockState state, BlockState other) {
		if (other.getBlock() == Blocks.SLIME_BLOCK) return false;
		if (other.getBlock() == Blocks.HONEY_BLOCK) return false;
		if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity", "snail_slime_block")))
			return false;
		if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic", "mulberry_jam_block")))
			return false;

		return super.canStickTo(state, other);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.setValue(WET, this.touchingWater(currentPos, worldIn));
	}

	public final boolean touchingWater(BlockPos blockPos, IBlockReader iBlockReader) {
		for (Direction direction : Direction.values()) {
			if (iBlockReader.getFluidState(blockPos.relative(direction)).is(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_OTHER;
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!state.getValue(WET)) {
			entity.makeStuckInBlock(state, new Vector3d(0.25D, 0.25D, 0.25D));
			if (entity instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) entity;
				living.addEffect(new EffectInstance(Effects.POISON, 150, 0, false, true, true));
				living.clearFire();
			}
		}
	}
}

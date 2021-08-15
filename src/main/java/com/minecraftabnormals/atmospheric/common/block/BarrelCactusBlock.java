package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

public class BarrelCactusBlock extends Block implements IPlantable, IGrowable {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D),
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

	private static final VoxelShape[] COLLISION_BY_AGE = new VoxelShape[]{
			Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D),
			Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D)};

	public BarrelCactusBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if (blockstate.getBlock() == this) {
			return blockstate.setValue(AGE, Math.min(3, blockstate.getValue(AGE) + 1));
		} else {
			return this.defaultBlockState();
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && state.getValue(AGE) < 3 || super.canBeReplaced(state, useContext);
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = state.getValue(AGE);
		if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(3) == 0)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, i + 1));
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return COLLISION_BY_AGE[state.getValue(AGE)];
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos)) {
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		return (downState.canSustainPlant(worldIn, pos.below(), Direction.UP, this) || downState.is(Blocks.RED_SAND)) && !worldIn.getBlockState(pos.above()).getMaterial().isLiquid();
	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			LivingEntity living = (LivingEntity) entityIn;
			living.addEffect(new EffectInstance(Effects.POISON, ((state.getValue(AGE) + 1) * 40)));
		}
		entityIn.hurt(AtmosphericDamageSources.BARREL_CACTUS, 0.5F * state.getValue(AGE));
		if (entityIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
			if (!entityIn.getCommandSenderWorld().isClientSide() && !serverplayerentity.isCreative()) {
				AtmosphericCriteriaTriggers.BARREL_CACTUS_PRICK.trigger(serverplayerentity);
			}
		}
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.DESERT;
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) {
		return defaultBlockState();
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 3;
	}

	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 3;
	}
}

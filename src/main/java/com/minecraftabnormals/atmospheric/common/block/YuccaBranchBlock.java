package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.abnormals_core.common.advancement.EmptyTrigger;
import com.minecraftabnormals.atmospheric.common.block.api.IYuccaPlant;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericTags;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class YuccaBranchBlock extends BushBlock implements IGrowable, IYuccaPlant {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_SNAPPED = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	public static final BooleanProperty SNAPPED = BooleanProperty.create("snapped");

	public YuccaBranchBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(SNAPPED, true));
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock().is(AtmosphericTags.YUCCA_LOGS);
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.mayPlaceOn(worldIn.getBlockState(pos.above()), worldIn, pos.above());
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.getValue(SNAPPED) ? SHAPE_SNAPPED : SHAPE;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SNAPPED);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		worldIn.setBlock(currentPos, stateIn.setValue(SNAPPED, !(worldIn.getBlockState(currentPos.below()).getBlock() instanceof YuccaBundleBlock)), 2);
		return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(SNAPPED) && worldIn.getBlockState(pos.below()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return state.getValue(SNAPPED);
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		if (state.getValue(SNAPPED) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(6) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
			worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
			worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			if (state.getValue(SNAPPED) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
				worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
				worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			}
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (!state.getValue(SNAPPED) && (player.getItemInHand(handIn).getItem() == Items.SHEARS)) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_213442_1_) -> {
				p_213442_1_.broadcastBreakEvent(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(SNAPPED, true), 2);
			return ActionResultType.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {
			this.onYuccaCollision(state, worldIn, pos, entityIn);
		} else if (entityIn instanceof ProjectileEntity && !state.getValue(SNAPPED)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, true));
		}
	}

	@Override
	public float getKnockbackForce() {
		return 0.85F;
	}

	@Override
	public DamageSource getDamageSource() {
		return AtmosphericDamageSources.YUCCA_BRANCH;
	}

	@Override
	public EmptyTrigger getCriteriaTrigger() {
		return AtmosphericCriteriaTriggers.YUCCA_BRANCH_PRICK;
	}
}

package com.teamabnormals.atmospheric.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;

public class YuccaBranchBlock extends BushBlock implements BonemealableBlock, YuccaPlant {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_SNAPPED = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	public static final BooleanProperty SNAPPED = BooleanProperty.create("snapped");

	public YuccaBranchBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(SNAPPED, true));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return null;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(AtmosphericBlockTags.YUCCA_LOGS);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return this.mayPlaceOn(worldIn.getBlockState(pos.above()), worldIn, pos.above());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return state.getValue(SNAPPED) ? SHAPE_SNAPPED : SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SNAPPED);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		worldIn.setBlock(currentPos, stateIn.setValue(SNAPPED, !(worldIn.getBlockState(currentPos.below()).getBlock() instanceof YuccaBundleBlock)), 2);
		return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
		return state.getValue(SNAPPED) && worldIn.getBlockState(pos.below()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return state.getValue(SNAPPED);
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		if (state.getValue(SNAPPED) && net.neoforged.neoforge.common.CommonHooks.canCropGrow(worldIn, pos, state, rand.nextInt(6) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
			worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
			worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			CommonHooks.fireCropGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			if (state.getValue(SNAPPED) && CommonHooks.canCropGrow(worldIn, pos, state, random.nextInt(5) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
				worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
				worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			}
			CommonHooks.fireCropGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!state.getValue(SNAPPED) && stack.is(Tags.Items.TOOLS_SHEAR)) {
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(handIn));
			level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(SNAPPED, true), 2);
			return ItemInteractionResult.SUCCESS;
		} else {
			return super.useItemOn(stack, state, level, pos, player, handIn, hit);
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn) {
		super.entityInside(state, level, pos, entityIn);
		if (entityIn instanceof LivingEntity && !(entityIn instanceof Bee)) {
			this.onYuccaCollision(state, level, pos, entityIn);
		} else if (entityIn instanceof Projectile && !state.getValue(SNAPPED)) {
			level.setBlockAndUpdate(pos, state.setValue(SNAPPED, true));
		}
	}

	@Override
	public float getKnockbackForce() {
		return 0.85F;
	}

	@Override
	public ResourceKey<DamageType> getDamageTypeKey() {
		return AtmosphericDamageTypes.YUCCA_BRANCH;
	}
}

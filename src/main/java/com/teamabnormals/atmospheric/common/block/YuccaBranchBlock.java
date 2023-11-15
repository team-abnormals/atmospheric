package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
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
import net.minecraftforge.common.ForgeHooks;

public class YuccaBranchBlock extends BushBlock implements BonemealableBlock, YuccaPlant {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_SNAPPED = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	public static final BooleanProperty SNAPPED = BooleanProperty.create("snapped");

	public YuccaBranchBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(SNAPPED, true));
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
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(SNAPPED) && worldIn.getBlockState(pos.below()).isAir();
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return state.getValue(SNAPPED);
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		if (state.getValue(SNAPPED) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(6) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
			worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
			worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			if (state.getValue(SNAPPED) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0) && worldIn.getBlockState(pos.below()).isAir()) {
				worldIn.setBlockAndUpdate(pos, state.setValue(SNAPPED, false));
				worldIn.setBlockAndUpdate(pos.below(), AtmosphericBlocks.YUCCA_BUNDLE.get().defaultBlockState());
			}
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!state.getValue(SNAPPED) && (player.getItemInHand(handIn).getItem() == Items.SHEARS)) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (p_213442_1_) -> {
				p_213442_1_.broadcastBreakEvent(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(SNAPPED, true), 2);
			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
		if (entityIn instanceof LivingEntity && !(entityIn instanceof Bee)) {
			this.onYuccaCollision(state, worldIn, pos, entityIn);
		} else if (entityIn instanceof Projectile && !state.getValue(SNAPPED)) {
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
}

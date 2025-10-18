package com.teamabnormals.atmospheric.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.datapack.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

public class AloeVeraBlock extends BushBlock implements BonemealableBlock {
	public static final VoxelShape SHAPE_SMALL = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	public static final VoxelShape SHAPE_MEDIUM = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	public static final VoxelShape SHAPE_LARGE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

	public AloeVeraBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return null;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int age = state.getValue(AGE);
		VoxelShape shape = age >= 4 ? SHAPE_LARGE : age >= 2 ? SHAPE_MEDIUM : SHAPE_SMALL;
		Vec3 vec3d = state.getOffset(worldIn, pos);
		return shape.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(Items.BONE_MEAL)) {
			return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
		} else {
			int i = state.getValue(AGE);
			if (i == 5 && stack.is(Tags.Items.TOOLS_SHEAR)) {
				RandomSource rand = player.getRandom();
				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
				level.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
				level.setBlockAndUpdate(pos, state.setValue(AGE, 2));

				popResource(level, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));

				return ItemInteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
			}
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && !entity.getType().is(AtmosphericEntityTypeTags.ALOE_IMMUNE)) {
			double chance = 0.1F;
			if (state.getValue(AGE) == 4) chance = 0.2F;
			if (state.getValue(AGE) == 5) chance = 0.4F;

			if (!level.isClientSide && state.getValue(AGE) > 2 && Math.random() <= chance) {
				entity.makeStuckInBlock(state, new Vec3(0.2F, 0.2D, 0.2F));
				entity.hurt(AtmosphericDamageTypes.aloeLeaves(level), 1.0F);
				if (entity instanceof ServerPlayer serverPlayer) {
					if (!entity.getCommandSenderWorld().isClientSide() && !serverPlayer.isCreative()) {
						AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.get().trigger(serverPlayer);
					}
				}
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		return downState.is(AtmosphericBlockTags.ALOE_PLACEABLE);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
		int age = state.getValue(AGE);
		if (age < 5) world.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
		else if (world.getBlockState(pos.above()).isAir() && world.getBlockState(pos.below()).is(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON)) {
			placeAt(world, pos, 2);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		boolean flag = worldIn.getBlockState(pos.below()).is(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON);
		int chance = flag ? 7 : 5;
		if (worldIn.getRawBrightness(pos.above(), 0) >= 12 && CommonHooks.canCropGrow(worldIn, pos, state, random.nextInt(chance) == 0)) {
			if (state.getValue(AGE) < 5) {
				worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
			} else if (flag) {
				if (AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) {
					AloeVeraTallBlock.placeAt(worldIn, AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState(), pos, 2);
				}
			}
			CommonHooks.fireCropGrowPost(worldIn, pos, state);
		}
	}

	@Nullable
	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return PathType.DAMAGE_OTHER;
	}

	@Nullable
	@Override
	public PathType getAdjacentBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity, PathType originalType) {
		return PathType.DANGER_OTHER;
	}

	public void placeAt(LevelAccessor world, BlockPos pos, int flags) {
		world.setBlock(pos, AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlock(pos.above(), AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
}

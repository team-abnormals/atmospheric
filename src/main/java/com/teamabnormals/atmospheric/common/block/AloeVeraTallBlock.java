package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

public class AloeVeraTallBlock extends DoublePlantBlock implements BonemealableBlock {
	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final VoxelShape SHAPE_TOP = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
	public static final IntegerProperty AGE = IntegerProperty.create("age", 6, 8);

	public AloeVeraTallBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 6).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(level, pos);
		VoxelShape shape = state.getValue(HALF) == DoubleBlockHalf.UPPER ? SHAPE_TOP : SHAPE;
		return shape.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, AGE);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		BlockState downState = level.getBlockState(pos.below());
		if (state.getBlock() instanceof AloeVeraTallBlock) {
			DoubleBlockHalf half = state.getValue(HALF);
			if (half == DoubleBlockHalf.UPPER) {
				return downState.getBlock() instanceof AloeVeraTallBlock;
			} else {
				return downState.is(AtmosphericBlockTags.ALOE_PLACEABLE);
			}
		}
		return super.mayPlaceOn(state, level, pos);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity) {
			if (!entity.getType().is(AtmosphericEntityTypeTags.ALOE_IMMUNE)) {
				entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75D, 0.8F));
			}

			RandomSource random = entity.getRandom();
			for (int i = 0; i < 3; i++) {
				double offsetX = random.nextFloat() * 0.6F;
				double offsetZ = random.nextFloat() * 0.45F;

				double x = pos.getX() + 0.5D + offsetX;
				double y = pos.getY() + 0.5D + (random.nextFloat() * 0.05F);
				double z = pos.getZ() + 0.65D + offsetZ;

				if (state.getValue(HALF) == DoubleBlockHalf.UPPER && level.isClientSide && level.getGameTime() % (9 / (state.getValue(AGE) - 5)) == 0)
					level.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}

			if (!level.isClientSide && state.getValue(AGE) > 3 && random.nextFloat() <= 0.4 && state.getValue(HALF) == DoubleBlockHalf.LOWER && !entity.getType().is(AtmosphericEntityTypeTags.ALOE_IMMUNE)) {
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
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericItems.ALOE_KERNELS.get());
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		double offsetX = rand.nextFloat() * 0.6F;
		double offsetZ = rand.nextFloat() * 0.45F;

		double x = pos.getX() + 0.5D + offsetX;
		double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
		double z = pos.getZ() + 0.65D + offsetZ;

		if (state.getValue(HALF) == DoubleBlockHalf.UPPER && level.isClientSide && level.getGameTime() % (6 / (state.getValue(AGE) - 5)) == 0)
			level.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(Items.BONE_MEAL)) {
			return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
		} else {
			int age = state.getValue(AGE);
			RandomSource rand = level.getRandom();
			if (stack.is(Tags.Items.TOOLS_SHEAR)) {

				stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

				for (int i = 0; i < (50 + rand.nextInt(50)); i++) {
					double offsetX = rand.nextFloat();
					double offsetZ = rand.nextFloat();

					double x = pos.getX() + offsetX;
					double y = pos.getY() + 0.5D + (rand.nextFloat());
					double z = pos.getZ() + 0.15D + offsetZ;

					if (level.isClientSide)
						level.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
				}

				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
				popResource(level, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), age - 5));
				popResource(level, pos, new ItemStack(AtmosphericItems.ALOE_KERNELS.get()));
				if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
					level.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
					level.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 18);
					level.setBlock(pos, AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 2), 18);
					popResource(level, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));
				} else {
					level.setBlock(pos.below(), AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5), 18);
					level.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
				}

				return ItemInteractionResult.sidedSuccess(level.isClientSide);
			} else {
				return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return this.mayPlaceOn(state, level, pos);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 8;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource rand, BlockPos pos, BlockState state) {
		return true;
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

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			boolean flag = level.getBlockState(pos.below()).is(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON);
			if (flag && state.getValue(AGE) < 8 && level.getRawBrightness(pos.above(), 0) >= 12 && CommonHooks.canCropGrow(level, pos, state, random.nextInt(7) == 0)) {
				level.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
				level.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, state.getValue(AGE) + 1));
				CommonHooks.fireCropGrowPost(level, pos, state);
			}
		}
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
		int age = state.getValue(AGE);
		DoubleBlockHalf half = state.getValue(HALF);
		if (age < 8) {
			if (half == DoubleBlockHalf.LOWER) {
				level.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
				level.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, age + 1));
			} else if (half == DoubleBlockHalf.UPPER) {
				level.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
				level.setBlockAndUpdate(pos.below(), state.setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, age + 1));
			}
		}
	}
}

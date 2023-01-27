package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Bee;
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
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(worldIn, pos);
		VoxelShape shape = state.getValue(HALF) == DoubleBlockHalf.UPPER ? SHAPE_TOP : SHAPE;
		return shape.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, AGE);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		if (state.getBlock() instanceof AloeVeraTallBlock) {
			DoubleBlockHalf half = state.getValue(HALF);
			if (half == DoubleBlockHalf.UPPER) {
				return downState.getBlock() instanceof AloeVeraTallBlock;
			} else {
				return downState.is(AtmosphericBlockTags.ALOE_PLACEABLE);
			}
		}
		return super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.DESERT;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!(entityIn instanceof Bee))
				entityIn.makeStuckInBlock(state, new Vec3(0.8F, 0.75D, 0.8F));
			RandomSource rand = RandomSource.create();

			for (int i = 0; i < 3; i++) {
				double offsetX = rand.nextFloat() * 0.6F;
				double offsetZ = rand.nextFloat() * 0.45F;

				double x = pos.getX() + 0.5D + offsetX;
				double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
				double z = pos.getZ() + 0.65D + offsetZ;

				if (state.getValue(HALF) == DoubleBlockHalf.UPPER && worldIn.isClientSide && worldIn.getGameTime() % (9 / (state.getValue(AGE) - 5)) == 0)
					worldIn.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}

			if (!worldIn.isClientSide && state.getValue(AGE) > 3 && Math.random() <= 0.4 && state.getValue(HALF) == DoubleBlockHalf.LOWER && !(entityIn instanceof Bee)) {
				entityIn.makeStuckInBlock(state, new Vec3(0.2F, 0.2D, 0.2F));
				entityIn.hurt(AtmosphericDamageSources.ALOE_LEAVES, 1.0F);
				if (entityIn instanceof ServerPlayer serverplayerentity) {
					if (!entityIn.getCommandSenderWorld().isClientSide() && !serverplayerentity.isCreative()) {
						AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.trigger(serverplayerentity);
					}
				}
			}
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericItems.ALOE_KERNELS.get());
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double offsetX = rand.nextFloat() * 0.6F;
		double offsetZ = rand.nextFloat() * 0.45F;

		double x = pos.getX() + 0.5D + offsetX;
		double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
		double z = pos.getZ() + 0.65D + offsetZ;

		if (state.getValue(HALF) == DoubleBlockHalf.UPPER && worldIn.isClientSide && worldIn.getGameTime() % (6 / (state.getValue(AGE) - 5)) == 0)
			worldIn.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int age = state.getValue(AGE);
		RandomSource rand = RandomSource.create();

		if (player.getItemInHand(handIn).getItem() == Items.SHEARS) {

			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(handIn);
			});

			for (int i = 0; i < (50 + rand.nextInt(50)); i++) {
				double offsetX = rand.nextFloat();
				double offsetZ = rand.nextFloat();

				double x = pos.getX() + offsetX;
				double y = pos.getY() + 0.5D + (rand.nextFloat());
				double z = pos.getZ() + 0.15D + offsetZ;

				if (worldIn.isClientSide)
					worldIn.addParticle(AtmosphericParticleTypes.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}

			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), age - 5));
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_KERNELS.get()));
			if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
				worldIn.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 18);
				worldIn.setBlock(pos, AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 2), 18);
				popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));
			} else {
				worldIn.setBlock(pos.below(), AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5), 18);
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
			}

			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return this.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 8;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 8;
	}

	@Nullable
	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DAMAGE_CACTUS;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		super.tick(state, worldIn, pos, random);
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			boolean flag = worldIn.getBlockState(pos.below()).is(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON);
			if (flag && state.getValue(AGE) < 8 && worldIn.getRawBrightness(pos.above(), 0) >= 12 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
				worldIn.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, state.getValue(AGE) + 1));
				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
		int age = state.getValue(AGE);
		DoubleBlockHalf half = state.getValue(HALF);
		if (age < 8) {
			if (half == DoubleBlockHalf.LOWER) {
				world.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
				world.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, age + 1));
			} else if (half == DoubleBlockHalf.UPPER) {
				world.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
				world.setBlockAndUpdate(pos.below(), state.setValue(HALF, DoubleBlockHalf.LOWER).setValue(AGE, age + 1));
			}
		}
	}
}

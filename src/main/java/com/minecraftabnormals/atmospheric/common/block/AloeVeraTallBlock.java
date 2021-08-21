package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericParticles;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class AloeVeraTallBlock extends DoublePlantBlock implements IGrowable {
	public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	public static final VoxelShape SHAPE_TOP = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
	public static final IntegerProperty AGE = IntegerProperty.create("age", 6, 8);

	public AloeVeraTallBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 6).setValue(HALF, DoubleBlockHalf.LOWER));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Vector3d vec3d = state.getOffset(worldIn, pos);
		VoxelShape shape = state.getValue(HALF) == DoubleBlockHalf.UPPER ? SHAPE_TOP : SHAPE;
		return shape.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HALF, AGE);
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		if (state.getBlock() instanceof AloeVeraTallBlock) {
			DoubleBlockHalf half = state.getValue(HALF);
			if (half == DoubleBlockHalf.UPPER) {
				return downState.getBlock() instanceof AloeVeraTallBlock;
			} else {
				return downState.canSustainPlant(worldIn, pos.below(), Direction.UP, this) || downState.is(Blocks.RED_SAND);
			}
		}
		return super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.DESERT;
	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity) {
			if (!(entityIn instanceof BeeEntity))
				entityIn.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
			Random rand = new Random();

			for (int i = 0; i < 3; i++) {
				double offsetX = rand.nextFloat() * 0.6F;
				double offsetZ = rand.nextFloat() * 0.45F;

				double x = pos.getX() + 0.5D + offsetX;
				double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
				double z = pos.getZ() + 0.65D + offsetZ;

				if (state.getValue(HALF) == DoubleBlockHalf.UPPER && worldIn.isClientSide && worldIn.getGameTime() % (9 / (state.getValue(AGE) - 5)) == 0)
					worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}

			if (!worldIn.isClientSide && state.getValue(AGE) > 3 && Math.random() <= 0.4 && state.getValue(HALF) == DoubleBlockHalf.LOWER && !(entityIn instanceof BeeEntity)) {
				entityIn.makeStuckInBlock(state, new Vector3d(0.2F, 0.2D, 0.2F));
				entityIn.hurt(AtmosphericDamageSources.ALOE_LEAVES, 1.0F);
				if (entityIn instanceof ServerPlayerEntity) {
					ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityIn;
					if (!entityIn.getCommandSenderWorld().isClientSide() && !serverplayerentity.isCreative()) {
						AtmosphericCriteriaTriggers.ALOE_VERA_PRICK.trigger(serverplayerentity);
					}
				}
			}
		}
	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericItems.ALOE_KERNELS.get());
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double offsetX = rand.nextFloat() * 0.6F;
		double offsetZ = rand.nextFloat() * 0.45F;

		double x = pos.getX() + 0.5D + offsetX;
		double y = pos.getY() + 0.5D + (rand.nextFloat() * 0.05F);
		double z = pos.getZ() + 0.65D + offsetZ;

		if (state.getValue(HALF) == DoubleBlockHalf.UPPER && worldIn.isClientSide && worldIn.getGameTime() % (6 / (state.getValue(AGE) - 5)) == 0)
			worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int age = state.getValue(AGE);
		Random rand = new Random();

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
					worldIn.addParticle(AtmosphericParticles.ALOE_BLOSSOM.get(), x, y, z, 0.03D, 0.0D, 0.03D);
			}

			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.YELLOW_BLOSSOMS.get(), age - 5));
			popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_KERNELS.get()));
			if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
				worldIn.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 18);
				worldIn.setBlock(pos, AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 2), 18);
				popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));
			} else {
				worldIn.setBlock(pos.below(), AtmosphericBlocks.ALOE_VERA.get().defaultBlockState().setValue(AloeVeraBlock.AGE, 5), 18);
				worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
			}

			return ActionResultType.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 8;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 8;
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			boolean flag = worldIn.getBlockState(pos.below()).is(Tags.Blocks.SAND_RED);
			if (!flag && state.getValue(AGE) < 8 && worldIn.getRawBrightness(pos.above(), 0) >= 12 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(7) == 0)) {
				worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
				worldIn.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(AGE, state.getValue(AGE) + 1));
				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
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

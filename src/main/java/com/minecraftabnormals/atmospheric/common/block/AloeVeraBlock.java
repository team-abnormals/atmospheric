package com.minecraftabnormals.atmospheric.common.block;

import com.minecraftabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.minecraftabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericItems;
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
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class AloeVeraBlock extends BushBlock implements IGrowable {
	public static final VoxelShape SHAPE_SMALL = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	public static final VoxelShape SHAPE_MEDIUM = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	public static final VoxelShape SHAPE_LARGE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

	public AloeVeraBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int age = state.getValue(AGE);
		VoxelShape shape = age >= 4 ? SHAPE_LARGE : age >= 2 ? SHAPE_MEDIUM : SHAPE_SMALL;
		Vector3d vec3d = state.getOffset(worldIn, pos);
		return shape.move(vec3d.x, vec3d.y, vec3d.z);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		int i = state.getValue(AGE);

		if (i == 5 && player.getItemInHand(handIn).getItem() == Items.SHEARS) {
			Random rand = new Random();
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> onBroken.broadcastBreakEvent(handIn));
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, 2));

			popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));

			return ActionResultType.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof BeeEntity)) {
			double chance = 0.1;

			if (state.getValue(AGE) == 3) chance = 0.1;
			if (state.getValue(AGE) == 4) chance = 0.2;
			if (state.getValue(AGE) == 5) chance = 0.4;

			if (!worldIn.isClientSide && state.getValue(AGE) > 2 && Math.random() <= chance) {
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

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		return downState.canSustainPlant(worldIn, pos.below(), Direction.UP, this) || mayPlaceOn(downState, worldIn, pos.below());
	}

	@Override
	public boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.is(Blocks.RED_SAND);
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.DESERT;
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		int age = state.getValue(AGE);
		if (age < 5) world.setBlockAndUpdate(pos, state.setValue(AGE, age + 1));
		else if (world.getBlockState(pos.above()).isAir() && world.getBlockState(pos.below()).is(Tags.Blocks.SAND_COLORLESS)) {
			placeAt(world, pos, 2);
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		boolean flag = worldIn.getBlockState(pos.below()).is(Tags.Blocks.SAND_RED);
		int chance = flag ? 5 : 7;
		if (worldIn.getRawBrightness(pos.above(), 0) >= 12 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(chance) == 0)) {
			if (state.getValue(AGE) < 5) {
				worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
			} else if (!flag) {
				AloeVeraTallBlock tallAloe = (AloeVeraTallBlock) (AtmosphericBlocks.TALL_ALOE_VERA.get());
				if (tallAloe.defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) {
					tallAloe.placeAt(worldIn, pos, 2);
				}
			}
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Nullable
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	public void placeAt(IWorld world, BlockPos pos, int flags) {
		world.setBlock(pos, AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlock(pos.above(), AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
}

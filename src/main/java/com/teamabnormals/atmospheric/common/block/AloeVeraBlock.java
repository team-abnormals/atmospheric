package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.AtmosphericDamageSources;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
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
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.PlantType;

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
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int i = state.getValue(AGE);

		if (i == 5 && player.getItemInHand(handIn).getItem() == Items.SHEARS) {
			RandomSource rand = RandomSource.create();
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> onBroken.broadcastBreakEvent(handIn));
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, 2));

			popResource(worldIn, pos, new ItemStack(AtmosphericItems.ALOE_LEAVES.get(), rand.nextInt(5) + 3));

			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && !(entityIn instanceof Bee)) {
			double chance = 0.1;

			if (state.getValue(AGE) == 3) chance = 0.1;
			if (state.getValue(AGE) == 4) chance = 0.2;
			if (state.getValue(AGE) == 5) chance = 0.4;

			if (!worldIn.isClientSide && state.getValue(AGE) > 2 && Math.random() <= chance) {
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
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		return downState.is(AtmosphericBlockTags.ALOE_PLACEABLE);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.DESERT;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
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
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		super.tick(state, worldIn, pos, random);
		boolean flag = worldIn.getBlockState(pos.below()).is(AtmosphericBlockTags.TALL_ALOE_GROWABLE_ON);
		int chance = flag ? 7 : 5;
		if (worldIn.getRawBrightness(pos.above(), 0) >= 12 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(chance) == 0)) {
			if (state.getValue(AGE) < 5) {
				worldIn.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
			} else if (flag) {
				if (AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().canSurvive(worldIn, pos) && worldIn.isEmptyBlock(pos.above())) {
					AloeVeraTallBlock.placeAt(worldIn, AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState(), pos, 2);
				}
			}
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Nullable
	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DAMAGE_CACTUS;
	}

	public void placeAt(LevelAccessor world, BlockPos pos, int flags) {
		world.setBlock(pos, AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.LOWER), flags);
		world.setBlock(pos.above(), AtmosphericBlocks.TALL_ALOE_VERA.get().defaultBlockState().setValue(AloeVeraTallBlock.HALF, DoubleBlockHalf.UPPER), flags);
	}
}

package com.teamabnormals.atmospheric.common.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Map;
import java.util.stream.Collectors;

public class CandleGateauBlock extends AbstractCandleBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = AbstractCandleBlock.LIT;

	protected static final VoxelShape GATEAU_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
	protected static final VoxelShape[] SHAPES = new VoxelShape[]{
			Shapes.or(GATEAU_SHAPE, Block.box(5.0D, 6.0D, 9.0D, 7.0D, 12.0D, 11.0D)),
			Shapes.or(GATEAU_SHAPE, Block.box(5.0D, 6.0D, 5.0D, 7.0D, 12.0D, 7.0D)),
			Shapes.or(GATEAU_SHAPE, Block.box(9.0D, 6.0D, 5.0D, 11.0D, 12.0D, 7.0D)),
			Shapes.or(GATEAU_SHAPE, Block.box(9.0D, 6.0D, 9.0D, 11.0D, 12.0D, 11.0D))
	};

	private static final Map<CandleBlock, CandleGateauBlock> BY_CANDLE = Maps.newHashMap();
	private static final Iterable<Vec3>[] PARTICLE_OFFSETS = new Iterable[]{
			ImmutableList.of(new Vec3(0.375D, 0.875D, 0.625D)),
			ImmutableList.of(new Vec3(0.375D, 0.875D, 0.375D)),
			ImmutableList.of(new Vec3(0.625D, 0.875D, 0.375D)),
			ImmutableList.of(new Vec3(0.625D, 0.875D, 0.625D))
	};

	private final Block candle;

	public CandleGateauBlock(Block candle, Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
		this.candle = candle;

		BY_CANDLE.put((CandleBlock) candle, this);
	}

	@Override
	protected MapCodec<? extends AbstractCandleBlock> codec() {
		return null;
	}

	@Override
	protected Iterable<Vec3> getParticleOffsets(BlockState state) {
		return PARTICLE_OFFSETS[state.getValue(FACING).get2DDataValue()];
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(FACING).get2DDataValue()];
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
			return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
		} else if (candleHit(hitResult) && stack.isEmpty() && state.getValue(LIT)) {
			extinguish(player, state, level, pos);
			return ItemInteractionResult.sidedSuccess(level.isClientSide);
		} else {
			return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		InteractionResult result = YuccaGateauBlock.eat(level, pos, AtmosphericBlocks.YUCCA_GATEAU.get().defaultBlockState().setValue(FACING, state.getValue(FACING)), player);
		if (result.consumesAction()) {
			dropResources(state, level, pos);
		}

		return result;
	}

	private static boolean candleHit(BlockHitResult hit) {
		return hit.getLocation().y - (double) hit.getBlockPos().getY() > 0.5;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(LIT, FACING);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericBlocks.YUCCA_GATEAU);
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return direction == Direction.DOWN && !state.canSurvive(level, pos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).isSolid();
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
		return CakeBlock.FULL_CAKE_SIGNAL;
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	public static BlockState byCandle(CandleBlock candle) {
		return BY_CANDLE.get(candle).defaultBlockState();
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
		if (ItemAbilities.FIRESTARTER_LIGHT == itemAbility && canLight(state)) {
			return state.setValue(BlockStateProperties.LIT, true);
		}

		return super.getToolModifiedState(state, context, itemAbility, simulate);
	}

	public static boolean canLight(BlockState state) {
		return state.is(AtmosphericBlockTags.CANDLE_YUCCA_GATEAUS, baseState -> baseState.hasProperty(LIT) && !state.getValue(LIT));
	}

	public Block getCandle() {
		return this.candle;
	}

	public static Iterable<CandleGateauBlock> getCandleGateaus() {
		return BuiltInRegistries.BLOCK.stream().filter(block -> Atmospheric.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()) && block instanceof CandleGateauBlock).map(block -> (CandleGateauBlock) block).collect(Collectors.toList());
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}
}

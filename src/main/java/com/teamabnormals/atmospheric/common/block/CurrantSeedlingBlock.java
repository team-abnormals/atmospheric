package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.common.block.grower.CurrantTreeGrower;
import com.teamabnormals.blueprint.common.block.wood.BlueprintSaplingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CurrantSeedlingBlock extends BlueprintSaplingBlock {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);

	public CurrantSeedlingBlock(Properties properties) {
		super(new CurrantTreeGrower(), properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int speed = level.getBiome(pos).get().coldEnoughToSnow(pos) ? 2 : 4;
		if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(speed) == 0) {
			if (!level.isAreaLoaded(pos, 1)) return;
			this.advanceTree(level, pos, state, random);
		}
	}
}

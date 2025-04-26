package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

public class LaurelSaplingBlock extends SaplingBlock {
	protected final TreeGrower oranges;
	protected final TreeGrower nether;

	public LaurelSaplingBlock(TreeGrower tree, TreeGrower oranges, TreeGrower nether, Properties properties) {
		super(tree, properties);
		this.oranges = oranges;
		this.nether = nether;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(BlockTags.SAND) || super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
		if (state.getValue(STAGE) == 0) {
			level.setBlock(pos, state.cycle(STAGE), 4);
		} else {
			boolean nether = level.dimensionTypeRegistration().is(BuiltinDimensionTypes.NETHER);
			boolean oranges = hasOranges(level, pos);
			TreeGrower grower = oranges ? (nether ? this.nether : this.oranges) : this.treeGrower;
			grower.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
		}
	}

	private static boolean hasOranges(LevelAccessor level, BlockPos pos) {
		for (BlockPos offsetPos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(3).west(3), pos.above(5).south(3).east(3))) {
			if (level.getBlockState(offsetPos).is(AtmosphericBlockTags.ORANGES)) {
				return true;
			}
		}

		return false;
	}
}

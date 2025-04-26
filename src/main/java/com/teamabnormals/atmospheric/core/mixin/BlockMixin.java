package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CactusBlock.class)
public abstract class BlockMixin extends Block {

	public BlockMixin(Properties properties) {
		super(properties);
	}

	@Override
	public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
		if (plant.is(Blocks.CACTUS) && state.is(AtmosphericBlocks.SNOWY_CACTUS.get())) {
			return TriState.TRUE;
		}
		return super.canSustainPlant(state, level, soilPosition, facing, plant);
	}
}
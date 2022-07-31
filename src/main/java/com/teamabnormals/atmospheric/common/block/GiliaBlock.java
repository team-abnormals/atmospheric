package com.teamabnormals.atmospheric.common.block;

import com.google.common.base.Supplier;
import com.teamabnormals.atmospheric.core.other.AtmosphericTags;
import com.teamabnormals.blueprint.common.block.BlueprintFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

public class GiliaBlock extends BlueprintFlowerBlock {

	public GiliaBlock(Supplier<MobEffect> effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(AtmosphericTags.YUCCA_PLANTABLE_ON);
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.DESERT;
	}
}

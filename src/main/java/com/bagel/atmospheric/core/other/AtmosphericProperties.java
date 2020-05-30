package com.bagel.atmospheric.core.other;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AtmosphericProperties {
	public static final Block.Properties TRAVERTINE = Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties ALOE_VERA = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().sound(SoundType.CROP);
}

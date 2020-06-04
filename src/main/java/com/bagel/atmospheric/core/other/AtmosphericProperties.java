package com.bagel.atmospheric.core.other;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class AtmosphericProperties {
	
	public static final Block.Properties ARID_SAND = Block.Properties.create(Material.SAND, MaterialColor.SAND).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5F).sound(SoundType.SAND);
	public static final Block.Properties RED_ARID_SAND = Block.Properties.create(Material.SAND, MaterialColor.ORANGE_TERRACOTTA).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5F).sound(SoundType.SAND);
	
	public static final Block.Properties IVORY_TRAVERTINE = Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties PEACH_TRAVERTINE = Block.Properties.create(Material.ROCK, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties PERSIMMON_TRAVERTINE = Block.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties SAFFRON_TRAVERTINE = Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(1.5F, 6.0F);
	
	public static final Block.Properties ALOE_VERA = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().sound(SoundType.CROP);
}

package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class AtmosphericProperties {
	public static final WoodSetProperties ROSEWOOD = WoodSetProperties.builder(MaterialColor.TERRACOTTA_MAGENTA).build();
	public static final WoodSetProperties MORADO = WoodSetProperties.builder(MaterialColor.COLOR_RED).build();
	public static final WoodSetProperties YUCCA = WoodSetProperties.builder(MaterialColor.COLOR_ORANGE).build();
	public static final WoodSetProperties ASPEN = WoodSetProperties.builder(MaterialColor.GOLD).leavesColor(MaterialColor.GOLD).build();
	public static final WoodSetProperties LAUREL = WoodSetProperties.builder(MaterialColor.TERRACOTTA_YELLOW).leavesColor(MaterialColor.TERRACOTTA_LIGHT_GREEN).build();
	public static final WoodSetProperties DRY_LAUREL = WoodSetProperties.builder(MaterialColor.TERRACOTTA_YELLOW).leavesColor(MaterialColor.SAND).build();
	public static final WoodSetProperties KOUSA = WoodSetProperties.builder(MaterialColor.TERRACOTTA_CYAN).leavesColor(MaterialColor.SNOW).build();
	public static final WoodSetProperties CURRANT = WoodSetProperties.builder(MaterialColor.TERRACOTTA_GRAY).leavesColor(MaterialColor.PODZOL).build();
	public static final WoodSetProperties GRIMWOOD = WoodSetProperties.builder(MaterialColor.TERRACOTTA_BLACK).build();

	public static final Block.Properties ARID_SAND = Block.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND);
	public static final Block.Properties RED_ARID_SAND = Block.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_ORANGE).strength(0.5F).sound(SoundType.SAND);

	public static final Block.Properties AGAVE = Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_CYAN).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ);
	public static final Block.Properties GOLDEN_GROWTHS = Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.GOLD).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XYZ);
	public static final Block.Properties CRUSTOSE_PATH = Block.Properties.of(Material.DIRT, MaterialColor.GOLD).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(PropertyUtil::never).isViewBlocking(PropertyUtil::never);
	public static final Block.Properties ARID_SPROUTS = Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.SAND).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.XYZ);

	public static final Block.Properties IVORY_TRAVERTINE = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
	public static final Block.Properties PEACH_TRAVERTINE = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
	public static final Block.Properties PERSIMMON_TRAVERTINE = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
	public static final Block.Properties SAFFRON_TRAVERTINE = Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
	public static final Block.Properties DOLERITE = Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(1.5F, 6.0F);

	public static final Block.Properties CARMINE_BLOCK = Block.Properties.of(Material.STONE, MaterialColor.COLOR_RED).sound(SoundType.TUFF).strength(0.5F);

	public static final Block.Properties ALOE_VERA = Block.Properties.of(Material.PLANT).noCollission().instabreak().randomTicks().sound(SoundType.CROP);
}

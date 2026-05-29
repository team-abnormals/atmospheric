package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericSoundEvents.AtmosphericSoundTypes;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.ToIntFunction;

public class AtmosphericProperties {
	public static final BlockSetType ROSEWOOD_BLOCK_SET = blockSetType("rosewood");
	public static final BlockSetType MORADO_BLOCK_SET = blockSetType("morado");
	public static final BlockSetType YUCCA_BLOCK_SET = blockSetType("yucca");
	public static final BlockSetType ASPEN_BLOCK_SET = blockSetType("aspen");
	public static final BlockSetType LAUREL_BLOCK_SET = blockSetType("laurel");
	public static final BlockSetType KOUSA_BLOCK_SET = blockSetType("kousa");
	public static final BlockSetType GRIMWOOD_BLOCK_SET = blockSetType("grimwood");

	public static final WoodType ROSEWOOD_WOOD_TYPE = woodSetType(ROSEWOOD_BLOCK_SET);
	public static final WoodType MORADO_WOOD_TYPE = woodSetType(MORADO_BLOCK_SET);
	public static final WoodType YUCCA_WOOD_TYPE = woodSetType(YUCCA_BLOCK_SET);
	public static final WoodType ASPEN_WOOD_TYPE = woodSetType(ASPEN_BLOCK_SET);
	public static final WoodType LAUREL_WOOD_TYPE = woodSetType(LAUREL_BLOCK_SET);
	public static final WoodType KOUSA_WOOD_TYPE = woodSetType(KOUSA_BLOCK_SET);
	public static final WoodType GRIMWOOD_WOOD_TYPE = woodSetType(GRIMWOOD_BLOCK_SET);

	public static final WoodSetProperties ROSEWOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_MAGENTA).build();
	public static final WoodSetProperties MORADO = WoodSetProperties.builder(MapColor.COLOR_RED).build();
	public static final WoodSetProperties YUCCA = WoodSetProperties.builder(MapColor.COLOR_ORANGE).leavesSound(AtmosphericSoundTypes.YUCCA_LEAVES).build();
	public static final WoodSetProperties ASPEN = WoodSetProperties.builder(MapColor.GOLD).leavesColor(MapColor.GOLD).build();
	public static final WoodSetProperties GREEN_ASPEN = WoodSetProperties.builder(MapColor.GOLD).leavesColor(MapColor.TERRACOTTA_LIGHT_GREEN).build();
	public static final WoodSetProperties LAUREL = WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).leavesColor(MapColor.TERRACOTTA_LIGHT_GREEN).build();
	public static final WoodSetProperties DRY_LAUREL = WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).leavesColor(MapColor.SAND).build();
	public static final WoodSetProperties KOUSA = WoodSetProperties.builder(MapColor.TERRACOTTA_CYAN).leavesColor(MapColor.SNOW).build();
	public static final WoodSetProperties CURRANT = WoodSetProperties.builder(MapColor.TERRACOTTA_GRAY).logSound(AtmosphericSoundTypes.CURRANT_STALK).leavesColor(MapColor.PODZOL).leavesSound(AtmosphericSoundTypes.CURRANT_LEAVES).build();
	public static final WoodSetProperties GRIMWOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_BLACK).build();

	public static final Properties ARID_SAND = Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(AtmosphericSoundTypes.ARID_SAND);
	public static final Properties RED_ARID_SAND = Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(0.5F).sound(AtmosphericSoundTypes.ARID_SAND);
	public static final Properties YUCCA_FLOWER = Properties.of().noCollission().strength(0.5F).sound(AtmosphericSoundTypes.YUCCA_LEAVES).offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY);
	public static final Properties ARID_SPROUTS = Properties.of().mapColor(MapColor.SAND).replaceable().noCollission().instabreak().sound(AtmosphericSoundTypes.ARID_SPROUTS).offsetType(OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY);

	public static Properties aridSandstone() {
		return aridSandstone(MapColor.SAND);
	}

	public static Properties redAridSandstone() {
		return aridSandstone(MapColor.TERRACOTTA_ORANGE);
	}

	public static Properties smoothAridSandstone() {
		return smoothAridSandstone(MapColor.SAND);
	}

	public static Properties smoothRedAridSandstone() {
		return smoothAridSandstone(MapColor.TERRACOTTA_ORANGE);
	}

	public static Properties aridSandstone(MapColor color) {
		return Properties.of().mapColor(color).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F).sound(AtmosphericSoundTypes.ARID_SANDSTONE);
	}

	public static Properties smoothAridSandstone(MapColor color) {
		return Properties.of().mapColor(color).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(AtmosphericSoundTypes.ARID_SANDSTONE);
	}

	public static final Properties AGAVE = Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY);
	public static final Properties GOLDEN_GROWTHS = Properties.of().mapColor(MapColor.GOLD).replaceable().noCollission().instabreak().sound(AtmosphericSoundTypes.GOLDEN_GROWTHS).offsetType(OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY);
	public static final Properties CRUSTOSE = BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).randomTicks().strength(0.6F).sound(SoundType.GRASS);
	public static final Properties CRUSTOSE_PATH = Properties.of().mapColor(MapColor.GOLD).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(PropertyUtil::always).isSuffocating(PropertyUtil::always);

	public static final Properties IVORY_TRAVERTINE = Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Properties PEACH_TRAVERTINE = Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Properties PERSIMMON_TRAVERTINE = Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Properties SAFFRON_TRAVERTINE = Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Properties DOLERITE = Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(AtmosphericSoundTypes.DOLERITE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Properties POLISHED_DOLERITE = Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(AtmosphericSoundTypes.POLISHED_DOLERITE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);

	public static final Properties CARMINE_BLOCK = Properties.of().mapColor(MapColor.COLOR_RED).sound(AtmosphericSoundTypes.CARMINE).strength(0.5F);

	public static final Properties ALOE_VERA = Properties.of().noCollission().instabreak().randomTicks().sound(AtmosphericSoundTypes.ALOE_VERA).pushReaction(PushReaction.DESTROY);
	public static final Properties ORANGE = Properties.of().mapColor(MapColor.COLOR_ORANGE).instabreak().sound(SoundType.HONEY_BLOCK).pushReaction(PushReaction.DESTROY);

	public static final BlockBehaviour.Properties YUCCA_GATEAU = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY);
	public static final BlockBehaviour.Properties CANDLE_YUCCA_GATEAU = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).lightLevel(litBlockEmission(3));

	private static ToIntFunction<BlockState> litBlockEmission(int level) {
		return (state) -> state.getValue(BlockStateProperties.LIT) ? level : 0;
	}

	public static BlockSetType blockSetType(String name) {
		return BlockSetTypeRegistryHelper.register(new BlockSetType(Atmospheric.MOD_ID + ":" + name));
	}

	public static WoodType woodSetType(BlockSetType type) {
		return WoodTypeRegistryHelper.registerWoodType(new WoodType(type.name(), type));
	}
}

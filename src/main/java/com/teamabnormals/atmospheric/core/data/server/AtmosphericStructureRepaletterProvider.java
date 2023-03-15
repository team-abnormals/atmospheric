package com.teamabnormals.atmospheric.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.EventPriority;

import static com.teamabnormals.atmospheric.core.AtmosphericConfig.COMMON;

public final class AtmosphericStructureRepaletterProvider extends StructureRepaletterProvider {

	public AtmosphericStructureRepaletterProvider(DataGenerator dataGenerator) {
		super(dataGenerator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerRepaletters() {
		this.simpleRepaletter("yucca_buttons_in_desert_villages", desertVillageSelector(), Blocks.JUNGLE_BUTTON, AtmosphericBlocks.YUCCA_BUTTON.get());
		this.simpleRepaletter("yucca_doors_in_desert_villages", desertVillageSelector(), Blocks.JUNGLE_DOOR, AtmosphericBlocks.YUCCA_DOOR.get());
		this.simpleRepaletter("yucca_fences_in_desert_villages", desertVillageSelector(), Blocks.JUNGLE_FENCE, AtmosphericBlocks.YUCCA_FENCE.get());
		this.simpleRepaletter("yucca_fence_gates_in_desert_villages", desertVillageSelector(), Blocks.JUNGLE_FENCE_GATE, AtmosphericBlocks.YUCCA_FENCE_GATE.get());
		this.simpleRepaletter("yucca_trapdoors_in_desert_villages", desertVillageSelector(), Blocks.JUNGLE_TRAPDOOR, AtmosphericBlocks.YUCCA_TRAPDOOR.get());
//		this.simpleRepaletter("yucca_ladders_in_desert_villages", desertVillageSelector(), Blocks.LADDER, AtmosphericBlocks.YUCCA_LADDER.get());
//		this.simpleRepaletter("yucca_chests_in_desert_villages", desertVillageSelector(), Blocks.CHEST, AtmosphericBlocks.YUCCA_CHESTS.getFirst().get());
//		this.simpleRepaletter("yucca_bookshelves_in_desert_villages", desertVillageSelector(), Blocks.BOOKSHELF, AtmosphericBlocks.YUCCA_BOOKSHELF.get());

		this.simpleRepaletter("grimwood_logs_in_ancient_cities", ancientCitySelector(), Blocks.DARK_OAK_LOG, AtmosphericBlocks.GRIMWOOD_LOG.get());
		this.simpleRepaletter("grimwood_fences_in_ancient_cities", ancientCitySelector(), Blocks.DARK_OAK_FENCE, AtmosphericBlocks.GRIMWOOD_FENCE.get());
		this.simpleRepaletter("grimwood_planks_in_ancient_cities", ancientCitySelector(), Blocks.DARK_OAK_PLANKS, AtmosphericBlocks.GRIMWOOD_PLANKS.get());
//		this.simpleRepaletter("grimwood_ladders_in_ancient_cities", ancientCitySelector(), Blocks.LADDER, AtmosphericBlocks.GRIMWOOD_LADDER.get());
//		this.simpleRepaletter("grimwood_chests_in_ancient_cities", ancientCitySelector(), Blocks.CHEST, AtmosphericBlocks.GRIMWOOD_CHESTS.getFirst().get());
	}

	public void simpleRepaletter(String name, ConditionedResourceSelector selector, Block toReplace, Block replacesWith) {
		this.registerRepaletter(name, selector, EventPriority.HIGH, new SimpleStructureRepaletter(toReplace, replacesWith));
	}

	private static ConditionedResourceSelector desertVillageSelector() {
		return selector(COMMON.yuccaDesertVillages, "yucca_desert_villages", BuiltinStructures.VILLAGE_DESERT.location());
	}

	private static ConditionedResourceSelector ancientCitySelector() {
		return selector(COMMON.grimwoodAncientCities, "grimwood_ancient_cities", BuiltinStructures.ANCIENT_CITY.location());
	}

	private static ConditionedResourceSelector selector(ForgeConfigSpec.ConfigValue<?> value, String key, ResourceLocation... structures) {
		return new ConditionedResourceSelector(new NamesResourceSelector(structures), new ConfigValueCondition(Atmospheric.location("config"), value, key, Maps.newHashMap(), false));
	}
}
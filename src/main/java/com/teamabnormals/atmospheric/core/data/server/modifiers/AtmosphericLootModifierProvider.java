package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;

public class AtmosphericLootModifierProvider extends LootModifierProvider {

	public AtmosphericLootModifierProvider(DataGenerator generator) {
		super(generator, Atmospheric.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.entry("ruined_portal").selects(BuiltInLootTables.RUINED_PORTAL).addModifier(new LootPoolEntriesModifier(false, 0, Collections.singletonList(LootItem.lootTableItem(AtmosphericItems.SHIMMERING_PASSIONFRUIT.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))).build())));
	}
}
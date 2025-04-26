package com.teamabnormals.atmospheric.core.data.server.modifiers;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.sequence;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addEntry;

public class AtmosphericDataRemolderProvider extends RemolderProvider {

	public AtmosphericDataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(Atmospheric.MOD_ID, Target.DATA_PACK, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("loot_table/chests/ruined_portal")
				.path("loot_table/chests/ruined_portal")
				.remolder(sequence(
						addEntry(0, LootItem.lootTableItem(AtmosphericItems.SHIMMERING_PASSION_FRUIT.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))).build()),
						addEntry(0, LootItem.lootTableItem(AtmosphericItems.GOLDEN_DRAGON_FRUIT.get()).setWeight(15).build())
				));
	}
}
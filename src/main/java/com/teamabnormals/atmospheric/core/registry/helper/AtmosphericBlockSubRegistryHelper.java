package com.teamabnormals.atmospheric.core.registry.helper;

import com.teamabnormals.atmospheric.common.item.MonkeyBrushItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class AtmosphericBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public AtmosphericBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public <B extends Block> DeferredBlock<B> createWallOrVerticalBlock(String name, String wallName, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier) {
		DeferredBlock<B> block = this.deferredRegister.register(wallName, wallSupplier);
		this.itemRegister.register(name, () -> new MonkeyBrushItem(supplier.get(), block.get(), new Item.Properties()));
		return block;
	}
}

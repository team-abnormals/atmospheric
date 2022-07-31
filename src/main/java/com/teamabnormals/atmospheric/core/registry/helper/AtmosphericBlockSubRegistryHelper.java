package com.teamabnormals.atmospheric.core.registry.helper;

import com.google.common.base.Supplier;
import com.teamabnormals.atmospheric.common.item.MonkeyBrushItem;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class AtmosphericBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public AtmosphericBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister(), parent.getBlockSubHelper().getDeferredRegister());
	}

	public <B extends Block> RegistryObject<B> createWallOrVerticalBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable CreativeModeTab group) {
		RegistryObject<B> block = this.deferredRegister.register(name, supplier);
		parent.getSubHelper(ForgeRegistries.ITEMS).getDeferredRegister().register(name, () -> new MonkeyBrushItem(block.get(), wallSupplier.get(), new Item.Properties().tab(group)));
		return block;
	}
}

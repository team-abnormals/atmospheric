package com.minecraftabnormals.atmospheric.core.registry.helper;

import com.google.common.base.Supplier;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.atmospheric.common.item.MonkeyBrushItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class AtmosphericBlockSubRegistryHelper extends BlockSubRegistryHelper {

	public AtmosphericBlockSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister(), parent.getBlockSubHelper().getDeferredRegister());
	}

	public <B extends Block> RegistryObject<B> createWallOrVerticalBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.deferredRegister.register(name, supplier);
		parent.getSubHelper(ForgeRegistries.ITEMS).getDeferredRegister().register(name, () -> new MonkeyBrushItem(block.get(), wallSupplier.get(), new Item.Properties().tab(group)));
		return block;
	}
}

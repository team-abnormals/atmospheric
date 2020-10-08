package com.minecraftabnormals.atmospheric.core;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;
import com.minecraftabnormals.atmospheric.common.item.MonkeyBrushItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class AtmosphericRegistryHelper extends RegistryHelper {

	public AtmosphericRegistryHelper(String modId) {
		super(modId);
	}

	public <B extends Block> RegistryObject<B> createWallOrVerticalBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.getDeferredBlockRegister().register(name, supplier);
		this.getDeferredItemRegister().register(name, () -> new MonkeyBrushItem(block.get(), wallSupplier.get(), new Item.Properties().group(group)));
		return block;
	}
}

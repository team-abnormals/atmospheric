package com.bagel.atmospheric.core.data;

import com.bagel.atmospheric.common.data.PassionVineBundleDispenseBehavior;
import com.bagel.atmospheric.common.data.PassionVineDispenseBehavior;
import com.bagel.atmospheric.core.registry.AtmosphericBlocks;

import net.minecraft.block.DispenserBlock;

public class AtmosphericDispenserBehaviors {
	
	public static void registerDispenserBehaviors() {
		DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE_BUNDLE.get().asItem(), new PassionVineBundleDispenseBehavior());
		DispenserBlock.registerDispenseBehavior(AtmosphericBlocks.PASSION_VINE.get().asItem(), new PassionVineDispenseBehavior());
	}
}

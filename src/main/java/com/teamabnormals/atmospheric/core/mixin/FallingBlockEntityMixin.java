package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {

	public FallingBlockEntityMixin(EntityType<?> entity, Level level) {
		super(entity, level);
	}

	@Override
	public ItemEntity spawnAtLocation(ItemStack stack, float p_19986_) {
		if (stack.is(AtmosphericBlocks.HANGING_CURRANT.get().asItem()))
			return null;
		return super.spawnAtLocation(stack, p_19986_);
	}
}
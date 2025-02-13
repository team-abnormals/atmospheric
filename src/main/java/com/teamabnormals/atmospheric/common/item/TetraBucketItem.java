package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetraBucketItem extends MobBucketItem {
	private static final Map<String, ResourceLocation> TYPE_CACHE = new HashMap<>();

	public TetraBucketItem(Item.Properties builder) {
		super(AtmosphericEntityTypes.TETRA::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, builder);
	}

	@Deprecated
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		CompoundTag tag = stack.getTag();
		if (tag != null) {
			if (tag.contains("TetraVariant", Tag.TAG_STRING)) {
				Registry<TetraVariant> registry = worldIn.registryAccess().registryOrThrow(AtmosphericRegistries.TETRA_VARIANT);
				TetraVariant tetra = registry.get(TYPE_CACHE.computeIfAbsent(tag.getString("TetraVariant"), ResourceLocation::new));
				tooltip.add(tetra.displayName().copy().withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
			}
		}
	}
}
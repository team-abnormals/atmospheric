package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.common.entity.Tetra;
import com.teamabnormals.atmospheric.common.entity.TetraVariant;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TetraBucketItem extends MobBucketItem {
	private static final Map<String, Optional<Holder<TetraVariant>>> TYPE_CACHE = new HashMap<>();

	public TetraBucketItem(Item.Properties builder) {
		super(AtmosphericEntityTypes.TETRA.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, builder);
	}

	@Deprecated
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		CustomData data = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
		if (data.isEmpty()) {
			return;
		}

		CompoundTag tag = data.copyTag();
		if (tag.contains(Tetra.BUCKET_VARIANT_TAG, CompoundTag.TAG_STRING) && context.registries() != null) {
			RegistryLookup<TetraVariant> registry = context.registries().lookupOrThrow(AtmosphericRegistries.TETRA_VARIANT);

			Optional<Holder<TetraVariant>> tetra = TYPE_CACHE.computeIfAbsent(tag.getString(Tetra.BUCKET_VARIANT_TAG), s ->
					Optional.ofNullable(ResourceLocation.tryParse(tag.getString(Tetra.BUCKET_VARIANT_TAG)))
							.map(loc -> ResourceKey.create(AtmosphericRegistries.TETRA_VARIANT, loc))
							.flatMap(registry::get));

			tetra.ifPresent(tetraVariantHolder -> tooltip.add(tetraVariantHolder.value().description().copy().withStyle(ChatFormatting.ITALIC)));
		}
	}
}
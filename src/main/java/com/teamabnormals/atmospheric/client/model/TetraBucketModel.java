package com.teamabnormals.atmospheric.client.model;

import com.teamabnormals.atmospheric.core.Atmospheric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetraBucketModel implements BakedModel {
	private final BakedModel model;
	private final ItemOverrides overrideList;

	public TetraBucketModel(Map<ResourceLocation, BakedModel> modelManager) {
		this.model = modelManager.get(new ResourceLocation(Atmospheric.MOD_ID, "models/item/tetra_bucket/neon_tetra_bucket"));
		this.overrideList = new Overrides(modelManager);
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
		return this.model.getQuads(state, side, rand);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return this.model.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return this.model.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return this.model.usesBlockLight();
	}

	@Override
	public boolean isCustomRenderer() {
		return this.model.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return this.model.getParticleIcon();
	}

	@Override
	public ItemOverrides getOverrides() {
		return this.overrideList;
	}

	private static class Overrides extends ItemOverrides {
		private final Map<ResourceLocation, BakedModel> modelManager;
		private final BakedModel model;
		private final Map<String, ResourceLocation> locationCache;
		private final Map<ResourceLocation, ResourceLocation> modelLocations;

		private Overrides(Map<ResourceLocation, BakedModel> modelManager) {
			this.modelManager = modelManager;
			this.model = modelManager.get(new ResourceLocation(Atmospheric.MOD_ID, "item/tetra_bucket/neon_tetra_bucket"));
			this.locationCache = new HashMap<>();
			this.modelLocations = new HashMap<>();
			for (ResourceLocation location : Minecraft.getInstance().getResourceManager().listResources("models/item/tetra_bucket", s -> s.getPath().endsWith(".json")).keySet())
				this.modelLocations.put(new ResourceLocation(location.getNamespace(), location.getPath().substring("models/item/tetra_bucket/".length(), location.getPath().length() - ".json".length())), new ResourceLocation(location.getNamespace(), location.getPath().substring("models/".length(), location.getPath().length() - ".json".length())));
		}

		@Nullable
		@Override
		public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int p_173469_) {
			if (stack.getTag() != null && stack.getTag().contains("TetraVariant", Tag.TAG_STRING)) {
				ResourceLocation variant = this.locationCache.computeIfAbsent(stack.getTag().getString("TetraVariant") + "_tetra_bucket", ResourceLocation::new);
				if (this.modelLocations.containsKey(variant))
					return this.modelManager.get(this.modelLocations.get(variant));
			}
			return this.model;
		}
	}
}

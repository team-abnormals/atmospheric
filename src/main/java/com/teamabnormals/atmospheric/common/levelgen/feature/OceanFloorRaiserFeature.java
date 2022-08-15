package com.teamabnormals.atmospheric.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.registry.AtmosphericNoiseParameters;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;

public class OceanFloorRaiserFeature extends Feature<NoneFeatureConfiguration> {

	public OceanFloorRaiserFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	//Artificially raises the "ocean" floor up 9 blocks in a decently natural looking way
	//Useful for needing more swamp-like terrain in the overworld
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		BlockPos origin = context.origin();
		int originX = origin.getX();
		int originZ = origin.getZ();
		int radius = 23;
		int minX = originX - radius;
		int minZ = originZ - radius;
		int maxX = originX + radius;
		int maxZ = originZ + radius;
		float radiusSquared = radius * radius;
		NormalNoise radiusOffsetNoise = NormalNoise.create(new XoroshiroRandomSource(context.random().nextLong()), AtmosphericNoiseParameters.FLOOR_RAISE_RADIUS_OFFSET.get());
		WorldGenLevel level = context.level();
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		int heightRaise = 9;
		int boostedHeightRaise = heightRaise + 3;
		int raiseDecrease = boostedHeightRaise - 1;
		ArrayList<Raise> raises = new ArrayList<>(Mth.square(radius * 2));
		ArrayList<DampeningPoint> dampeningPoints = new ArrayList<>();
		for (int x = minX; x <= maxX; x++) {
			int xDistanceSqr = Mth.square(originX - x);
			for (int z = minZ; z <= maxZ; z++) {
				int distanceSqr = xDistanceSqr + Mth.square(originZ - z);
				if (distanceSqr > radiusSquared && distanceSqr > radiusSquared + radiusSquared * 0.5F * radiusOffsetNoise.getValue(x, 0.0F, z)) continue;
				int y = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z) - 1;
				if (y < 45) {
					dampeningPoints.add(new DampeningPoint(x, z));
					continue;
				}
				mutable.set(x, y, z);
				BlockState state = level.getBlockState(mutable);
				if (state.is(Blocks.MOSSY_COBBLESTONE)) state = Blocks.GRAVEL.defaultBlockState();
				if (canReplace(state)) {
					raises.add(new Raise(state, mutable.immutable(), distanceSqr / radiusSquared));
				} else {
					dampeningPoints.add(new DampeningPoint(x, z));
				}
			}
		}
		double dampeningPower = 1.0D / raiseDecrease;
		for (DampeningPoint dampeningPoint : dampeningPoints) {
			int pointX = dampeningPoint.x;
			int pointZ = dampeningPoint.z;
			for (Raise raise : raises) {
				BlockPos pos = raise.pos;
				raise.dampeningFactor += (1.0D / (Mth.square(pos.getX() - pointX) + Mth.square(pos.getZ() - pointZ))) * dampeningPower;
			}
		}
		for (Raise raise : raises) {
			int increase = boostedHeightRaise - (int) (raise.dampeningFactor * raiseDecrease);
			if (increase > heightRaise) increase = heightRaise;
			BlockPos pos = raise.pos;
			int totalY = pos.getY() + increase;
			if (totalY > 60) increase -= totalY - 60;
			mutable.set(pos);
			BlockState raiseState = raise.state;
			for (int i = 0; i < increase; i++) {
				mutable.setY(mutable.getY() + 1);
				BlockState state = level.getBlockState(mutable);
				if (canReplace(state) || state.is(Blocks.MOSSY_COBBLESTONE) || state.getFluidState().is(FluidTags.WATER)) {
					level.setBlock(mutable, raiseState, 2);
				} else break;
			}
		}
		return raises.size() > 0;
	}

	private static boolean canReplace(BlockState state) {
		return state.is(Tags.Blocks.GRAVEL) || state.is(BlockTags.DIRT) || state.is(BlockTags.SAND) || state.is(Blocks.CLAY) || state.is(BlockTags.BASE_STONE_OVERWORLD);
	}

	private static final class Raise {
		private final BlockState state;
		private final BlockPos pos;
		private double dampeningFactor;

		private Raise(BlockState state, BlockPos pos, double dampeningFactor) {
			this.state = state;
			this.pos = pos;
			this.dampeningFactor = dampeningFactor;
		}
	}

	private static record DampeningPoint(int x, int z) {}

}

package com.teamabnormals.atmospheric.common.entity.projectile;

import com.teamabnormals.atmospheric.common.block.PassionVineBlock;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.atmospheric.core.registry.AtmosphericEntityTypes;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ThrownPassionVineCoil extends ThrowableItemProjectile {

	public ThrownPassionVineCoil(Level level, LivingEntity throwerIn) {
		super(AtmosphericEntityTypes.PASSION_VINE_COIL.get(), throwerIn, level);
	}

	public ThrownPassionVineCoil(EntityType<? extends ThrownPassionVineCoil> type, Level level) {
		super(type, level);
	}

	@Override
	protected Item getDefaultItem() {
		return AtmosphericItems.PASSION_VINE_COIL.get();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions particle = new ItemParticleOption(ParticleTypes.ITEM, this.getItem());
			for (int i = 0; i < 8; ++i) {
				this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		Level level = this.level();
		BlockPos pos = result.getBlockPos();
		Direction direction = result.getDirection().getAxis().isHorizontal() ? result.getDirection() : this.getOwner().getDirection().getOpposite();
		BlockPos offsetPos = pos.relative(direction);

		while (level.getBlockState(offsetPos).is(AtmosphericBlocks.PASSION_VINE.get())) {
			offsetPos = offsetPos.below();
		}
		if (!level.getBlockState(offsetPos).isAir()) {
			this.removeVine(offsetPos, 8);
		} else {
			for (int i = 0; i < 4; i++) {
				BlockState state = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction);
				if (state.canSurvive(level, offsetPos)) {
					level.setBlockAndUpdate(offsetPos, state);
					offsetPos = offsetPos.below();
					int counter = 0;
					while (counter < 7 && level.getBlockState(offsetPos).isAir()) {
						level.setBlockAndUpdate(offsetPos, state);
						offsetPos = offsetPos.below();
						counter++;
					}

					this.removeVine(offsetPos, 7 - counter);
					return;
				} else {
					direction = direction.getClockWise();
				}
				this.removeVine(offsetPos, 8);
			}
		}
	}

	private void removeVine(BlockPos pos, int dropVines) {
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
		if (dropVines > 0) {
			Block.popResource(this.level(), pos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), dropVines));
		}
	}
}

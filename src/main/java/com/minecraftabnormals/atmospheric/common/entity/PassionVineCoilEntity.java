package com.minecraftabnormals.atmospheric.common.entity;

import com.minecraftabnormals.atmospheric.common.block.PassionVineBlock;
import com.minecraftabnormals.atmospheric.common.block.PassionVineBundleBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PassionVineCoilEntity extends ThrowableItemProjectile {

	public PassionVineCoilEntity(Level worldIn, LivingEntity throwerIn) {
		super(EntityType.SNOWBALL, throwerIn, worldIn);
	}

	protected Item getDefaultItem() {
		return Items.SNOWBALL;
	}

	@OnlyIn(Dist.CLIENT)
	private ParticleOptions makeParticle() {
		ItemStack itemstack = this.getItemRaw();
		return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions iparticledata = this.makeParticle();
			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		Level worldIn = this.getCommandSenderWorld();
		Direction direction = result.getDirection().getAxis().isHorizontal() ? result.getDirection().getOpposite() : this.getOwner().getDirection();

		BlockPos pos = this.blockPosition();
		BlockPos landedPos = pos.relative(direction);

		if (!worldIn.getBlockState(pos).is(AtmosphericBlocks.PASSION_VINE.get()) && worldIn.getBlockState(landedPos).is(AtmosphericBlocks.PASSION_VINE.get())) {
			pos = landedPos;
		}

		BlockPos nextPos = pos.relative(Direction.DOWN);
		BlockState nextBlock = worldIn.getBlockState(nextPos);

		int counter;
		if (!level.getBlockState(pos).isAir()) {
			if (level.getBlockState(pos).is(AtmosphericBlocks.PASSION_VINE.get())) {
				while (true) {
					if (nextBlock.is(AtmosphericBlocks.PASSION_VINE.get())) {
						nextPos = nextPos.below();
						nextBlock = worldIn.getBlockState(nextPos);
					} else {
						if (nextBlock.isAir()) {
							pos = nextPos;
							nextPos = pos.below();
							nextBlock = worldIn.getBlockState(nextPos);
							break;
						} else {
							this.removeVine(nextPos, true);
							return;
						}
					}
				}
			} else {
				this.removeVine(nextPos, true);
				return;
			}
		}

		if (AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite()).canSurvive(worldIn, pos)) {
			BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite());
			worldIn.setBlockAndUpdate(pos, vine);
			counter = 7;
			while (counter > 0) {
				if (nextBlock.isAir()) {
					worldIn.setBlockAndUpdate(nextPos, vine);
					counter = counter - 1;
					nextPos = nextPos.below();
					nextBlock = worldIn.getBlockState(nextPos);
				} else {
					break;
				}
			}
			PassionVineBundleBlock.popResource(worldIn, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
		} else {
			int k1 = 0;
			while (true) {
				if (direction == Direction.NORTH) {
					direction = Direction.EAST;
				} else if (direction == Direction.EAST) {
					direction = Direction.SOUTH;
				} else if (direction == Direction.SOUTH) {
					direction = Direction.WEST;
				} else if (direction == Direction.WEST) {
					direction = Direction.NORTH;
				}
				k1 = k1 + 1;
				if (AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite()).canSurvive(worldIn, pos)) {
					BlockState vine = AtmosphericBlocks.PASSION_VINE.get().defaultBlockState().setValue(PassionVineBlock.FACING, direction.getOpposite());
					worldIn.setBlockAndUpdate(pos, vine);
					counter = 7;
					while (counter > 0) {
						if (nextBlock.isAir()) {
							worldIn.setBlockAndUpdate(nextPos, vine);
							counter = counter - 1;
							nextPos = nextPos.below();
							nextBlock = worldIn.getBlockState(nextPos);
						} else {
							break;
						}
					}
					PassionVineBundleBlock.popResource(worldIn, nextPos.above(), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
					break;
				} else if (k1 >= 3) {
					worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
					PassionVineBundleBlock.popResource(worldIn, nextPos.above(), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));
					break;
				}
			}
		}
		this.removeVine(pos, false);
	}

	private void removeVine(BlockPos nextPos, boolean doDrops) {
		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
		if (doDrops)
			PassionVineBundleBlock.popResource(this.level, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));
	}
}

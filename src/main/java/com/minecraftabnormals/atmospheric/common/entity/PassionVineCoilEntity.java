package com.minecraftabnormals.atmospheric.common.entity;

import com.minecraftabnormals.atmospheric.common.block.PassionVineBlock;
import com.minecraftabnormals.atmospheric.common.block.PassionVineBundleBlock;
import com.minecraftabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PassionVineCoilEntity extends ProjectileItemEntity {

	public PassionVineCoilEntity(World worldIn, LivingEntity throwerIn) {
		super(EntityType.SNOWBALL, throwerIn, worldIn);
	}

	protected Item getDefaultItem() {
		return Items.SNOWBALL;
	}

	@OnlyIn(Dist.CLIENT)
	private IParticleData makeParticle() {
		ItemStack itemstack = this.getItemRaw();
		return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			IParticleData iparticledata = this.makeParticle();
			for (int i = 0; i < 8; ++i) {
				this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	protected void onHitBlock(BlockRayTraceResult result) {
		World worldIn = this.getCommandSenderWorld();
		Direction direction = result.getDirection().getAxis().isHorizontal() ? result.getDirection().getOpposite() : this.getOwner().getDirection();

		BlockPos pos = this.blockPosition();
		BlockPos landedPos = pos.relative(direction);

		if (!worldIn.getBlockState(pos).is(AtmosphericBlocks.PASSION_VINE.get()) && worldIn.getBlockState(landedPos).is(AtmosphericBlocks.PASSION_VINE.get())) {
			pos = landedPos;
		}

		BlockPos nextPos = pos.relative(Direction.DOWN);
		BlockState nextBlock = worldIn.getBlockState(nextPos);

		int counter;
		if (!level.getBlockState(pos).isAir(worldIn, pos)) {
			if (level.getBlockState(pos).is(AtmosphericBlocks.PASSION_VINE.get())) {
				while (true) {
					if (nextBlock.is(AtmosphericBlocks.PASSION_VINE.get())) {
						nextPos = nextPos.below();
						nextBlock = worldIn.getBlockState(nextPos);
					} else {
						if (nextBlock.isAir(worldIn, nextPos)) {
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
				if (nextBlock.isAir(worldIn, nextPos)) {
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
						if (nextBlock.isAir(worldIn, nextPos)) {
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
			this.remove();
		}
		if (doDrops)
			PassionVineBundleBlock.popResource(this.level, nextPos.relative(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));
	}
}

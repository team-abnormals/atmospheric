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
		ItemStack itemstack = this.func_213882_k();
		return itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 3) {
			IParticleData iparticledata = this.makeParticle();
			for (int i = 0; i < 8; ++i) {
				this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	protected void func_230299_a_(BlockRayTraceResult result) {
		World worldIn = this.getEntityWorld();
		Direction direction = result.getFace().getAxis().isHorizontal() ? result.getFace().getOpposite() : this.func_234616_v_().getHorizontalFacing();

		BlockPos pos = this.getPosition();
		BlockPos landedPos = pos.offset(direction);

		if (!worldIn.getBlockState(pos).isIn(AtmosphericBlocks.PASSION_VINE.get()) && worldIn.getBlockState(landedPos).isIn(AtmosphericBlocks.PASSION_VINE.get())) {
			pos = landedPos;
		}

		BlockPos nextPos = pos.offset(Direction.DOWN);
		BlockState nextBlock = worldIn.getBlockState(nextPos);

		int counter;
		if (!world.getBlockState(pos).isAir(worldIn, pos)) {
			if (world.getBlockState(pos).isIn(AtmosphericBlocks.PASSION_VINE.get())) {
				while (true) {
					if (nextBlock.isIn(AtmosphericBlocks.PASSION_VINE.get())) {
						nextPos = nextPos.down();
						nextBlock = worldIn.getBlockState(nextPos);
					} else {
						if (nextBlock.isAir(worldIn, nextPos)) {
							pos = nextPos;
							nextPos = pos.down();
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

		if (AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)) {
			BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
			worldIn.setBlockState(pos, vine);
			counter = 7;
			while (counter > 0) {
				if (nextBlock.isAir(worldIn, nextPos)) {
					worldIn.setBlockState(nextPos, vine);
					counter = counter - 1;
					nextPos = nextPos.down();
					nextBlock = worldIn.getBlockState(nextPos);
				} else {
					break;
				}
			}
			PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
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
				if (AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite()).isValidPosition(worldIn, pos)) {
					BlockState vine = AtmosphericBlocks.PASSION_VINE.get().getDefaultState().with(PassionVineBlock.FACING, direction.getOpposite());
					worldIn.setBlockState(pos, vine);
					counter = 7;
					while (counter > 0) {
						if (nextBlock.isAir(worldIn, nextPos)) {
							worldIn.setBlockState(nextPos, vine);
							counter = counter - 1;
							nextPos = nextPos.down();
							nextBlock = worldIn.getBlockState(nextPos);
						} else {
							break;
						}
					}
					PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.up(), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), counter));
					break;
				} else if (k1 >= 3) {
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
					PassionVineBundleBlock.spawnAsEntity(worldIn, nextPos.up(), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));
					break;
				}
			}
		}
		this.removeVine(pos, false);
	}

	private void removeVine(BlockPos nextPos, boolean doDrops) {
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.remove();
		}
		if (doDrops)
			PassionVineBundleBlock.spawnAsEntity(this.world, nextPos.offset(Direction.UP), new ItemStack(AtmosphericBlocks.PASSION_VINE.get(), 8));
	}
}

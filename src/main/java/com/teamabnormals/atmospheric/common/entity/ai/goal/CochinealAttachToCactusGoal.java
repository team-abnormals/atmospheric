package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class CochinealAttachToCactusGoal extends Goal {
	private final Cochineal cochineal;
	private BlockPos cactusPos;
	private Direction cactusSide;
	private int nextStartTicks;
	private int hops;

	public CochinealAttachToCactusGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.nextStartTicks > 0) {
			--this.nextStartTicks;
			return false;
		}

		this.nextStartTicks = this.adjustedTickDelay(20);

		if (this.cochineal.isInFluidType() || this.cochineal.isAttachedToCactus())
			return false;
		if (this.cochineal.isOnSuckleCooldown() || this.cochineal.getRandom().nextInt(this.adjustedTickDelay(this.cochineal.isBaby() ? 10 : 800)) != 0)
			return false;

		BlockPos blockpos = this.findCactus();
		if (blockpos != null) {
			this.cactusPos = blockpos;
			this.cactusSide = this.cochineal.getClosestVisibleCactusFace(this.cactusPos);
			return true;
		}

		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.cochineal.isInFluidType() && !this.cochineal.isAttachedToCactus() && this.cochineal.isSuckleable(this.cactusPos) && this.cochineal.hasSpaceOnCactusSide(this.cactusPos, this.cactusSide) && this.hops > 0;
	}

	@Override
	public void start() {
		this.hops = 4;
	}

	@Override
	public void tick() {
		double x = this.cactusPos.getX() + 0.5D;
		double y = this.cactusPos.getY();
		double z = this.cactusPos.getZ() + 0.5D;
		if (this.cochineal.distanceToSqr(x + this.cactusSide.getStepX(), y, z + this.cactusSide.getStepZ()) < 1.0D) {
			this.cochineal.attachToCactus(this.cactusPos, this.cactusSide);
		} else if (this.cochineal.canLeap()) {
			CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
			x += this.cactusSide.getStepX() * 1.2D;
			z += this.cactusSide.getStepZ() * 1.2D;
			if (control.canReach(x, y, z))
				control.leapTo(x, y, z);
			this.hops--;
		}
	}

	private BlockPos findCactus() {
		BlockPos blockpos = this.cochineal.blockPosition();
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for(int r = 0; r < 10; ++r) {
			for(int x = 0; x <= r; x = x > 0 ? -x : 1 - x) {
				for(int z = x < r && x > -r ? r : 0; z <= r; z = z > 0 ? -z : 1 - z) {
					for(int y = 0; y <= 2; y = y > 0 ? -y : 1 - y) {
						mutable.setWithOffset(blockpos, x, y, z);
						if (this.cochineal.isSuckleable(mutable)) {
							List<BlockPos> list = Lists.newArrayList();
							for (int i = -3; i <= 3; i++) {
								BlockPos blockpos1 = mutable.relative(Axis.Y, i);
								if (this.cochineal.isSuckleable(blockpos1) && this.cochineal.getClosestVisibleCactusFace(blockpos1) != null)
									list.add(blockpos1);
							}

							if (!list.isEmpty())
								return list.get(this.cochineal.getRandom().nextInt(list.size()));
						}
					}
				}
			}
		}

		return null;
	}
}
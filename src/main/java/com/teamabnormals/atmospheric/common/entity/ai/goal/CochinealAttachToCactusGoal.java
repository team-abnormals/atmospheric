package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.google.common.collect.Lists;
import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class CochinealAttachToCactusGoal extends Goal {
	private final Cochineal cochineal;
	private BlockPos cactusPos;
	private BlockPos cactusFacePos;
	private Direction cactusFace;
	private int nextStartTicks;

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

		if (this.cochineal.isInFluidType() || !this.cochineal.isOnGround())
			return false;
		//if (!this.cochineal.isBaby() && this.cochineal.getRandom().nextInt(this.adjustedTickDelay(200)) != 0)
		//	return false;

		BlockPos blockpos = this.findCactus();
		if (blockpos != null) {
			this.cactusPos = blockpos;
			this.cactusFace = this.cochineal.getClosestCactusFace(this.cactusPos);
			this.cactusFacePos = this.cactusPos.relative(this.cactusFace);
			return true;
		}

		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.cochineal.isInFluidType() && this.cochineal.isSuckleableCactus(this.cactusPos) && this.cochineal.getLevel().isEmptyBlock(this.cactusFacePos);
	}

	@Override
	public void tick() {
		if (this.cochineal.blockPosition().equals(this.cactusFacePos)) {
			this.cochineal.attachToCactus(this.cactusPos, this.cactusFace);
		} else if (this.cochineal.canLeap()) {
			CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
			double x = this.cactusFacePos.getX() + 0.5D + this.cactusFace.getStepX() * 0.15D;
			double y = this.cactusFacePos.getY() + 0.5D;
			double z = this.cactusFacePos.getZ() + 0.5D + this.cactusFace.getStepZ() * 0.15D;
			if (control.canReach(x, y, z)) {
				control.leapTo(x, y, z);
			}
		}
	}

	private BlockPos findCactus() {
		BlockPos blockpos = this.cochineal.blockPosition();
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for(int r = 0; r < 12; ++r) {
			for(int x = 0; x <= r; x = x > 0 ? -x : 1 - x) {
				for(int z = x < r && x > -r ? r : 0; z <= r; z = z > 0 ? -z : 1 - z) {
					for(int y = 0; y <= 2; y = y > 0 ? -y : 1 - y) {
						mutable.setWithOffset(blockpos, x, y, z);
						if (this.cochineal.isSuckleableCactus(mutable)) {
							List<BlockPos> list = Lists.newArrayList();
							for (int i = -3; i <= 3; i++) {
								BlockPos blockpos1 = mutable.relative(Axis.Y, i);
								if (this.cochineal.isSuckleableCactus(blockpos1) && this.cochineal.getClosestCactusFace(blockpos1) != null) {
									list.add(blockpos1);
								}
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
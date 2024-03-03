package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class CochinealRandomHopGoal extends Goal {
	private final Cochineal cochineal;
	private int hops;
	private int stuckTime;
	private float direction;

	public CochinealRandomHopGoal(Cochineal cochineal) {
		this.cochineal = cochineal;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		return !this.cochineal.isInFluidType() && !this.cochineal.isAttachedToCactus() && this.cochineal.getNoActionTime() < 100 && this.cochineal.getRandom().nextInt(this.adjustedTickDelay(this.cochineal.isBaby() ? 120 : 40)) == 0;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.cochineal.isInFluidType() && this.hops > 0;
	}

	@Override
	public void start() {
		this.hops = this.cochineal.getRandom().nextInt(7) + 2;
		this.stuckTime = 0;

		BlockPos blockpos = this.cochineal.getLastHabitatBiomePos();
		if (blockpos != null && !this.cochineal.getLevel().getBiome(this.cochineal.blockPosition()).is(AtmosphericBiomeTags.IS_COCHINEAL_HABITAT))
			this.direction = (float) Mth.atan2(blockpos.getZ() - this.cochineal.getZ(), blockpos.getX() - this.cochineal.getX()) - Mth.HALF_PI;
		else
			this.direction = this.cochineal.getYRot();
	}

	@Override
	public void tick() {
		if (this.cochineal.canLeap()) {
			CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
			Vec3 vec3 = this.cochineal.position().add(new Vec3(-Math.sin(this.direction) * 32.0D, 0.0D, Math.cos(this.direction) * 32.0D));
			Vec3 vec31 = DefaultRandomPos.getPosTowards(this.cochineal, 12, 7, vec3, Math.PI / 4.0D);
			if (vec31 != null) {
				vec31.add(0.5D, 0.0D, 0.5D);
				if (control.canReach(vec31.x, vec31.y, vec31.z)) {
					control.leapTo(vec31.x, vec31.y, vec31.z);
					--this.hops;
					this.stuckTime = 0;
					return;
				}
			}

			if (++this.stuckTime > this.adjustedTickDelay(80)) {
				this.direction = this.cochineal.getRandom().nextFloat() * Mth.TWO_PI;
				this.stuckTime = 0;
			}
		}
	}
}
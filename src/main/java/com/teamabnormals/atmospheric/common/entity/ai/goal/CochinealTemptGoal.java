package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;

public class CochinealTemptGoal extends TemptGoal {
	private final Cochineal cochineal;

	public CochinealTemptGoal(Cochineal cochineal, double speed, Ingredient ingredient) {
		super(cochineal, speed, ingredient, false);
		this.cochineal = cochineal;
	}

	@Override
	public boolean canUse() {
		return !this.cochineal.isAttachedToCactus() && super.canUse();
	}

	@Override
	public void tick() {
		if (this.cochineal.isInFluidType()) {
			super.tick();
		} else if (this.cochineal.canLeap()) {
			this.mob.getLookControl().setLookAt(this.player, (float) (this.mob.getMaxHeadYRot() + 20), (float) this.mob.getMaxHeadXRot());
			double d0 = this.mob.distanceToSqr(this.player);
			if (d0 >= 9.0D) {
				int i = d0 < 36.0D ? 3 : 7;
				int j = d0 < 36.0D ? 1 : 3;
				CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
				Vec3 vec3 = DefaultRandomPos.getPosTowards(this.mob, i, j, this.player.position(), Math.PI / 6.0D);
				if (vec3 != null) {
					vec3.add(0.5D, 0.0D, 0.5D);
					if (control.canReach(vec3.x, vec3.y, vec3.z))
						control.leapTo(vec3.x, vec3.y, vec3.z);
				}
			}
		}
	}
}
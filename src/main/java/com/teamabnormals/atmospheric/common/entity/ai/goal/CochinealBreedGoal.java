package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.phys.Vec3;

public class CochinealBreedGoal extends BreedGoal {
	private final Cochineal cochineal;

	public CochinealBreedGoal(Cochineal cochineal, double speed) {
		super(cochineal, speed);
		this.cochineal = cochineal;
	}

	@Override
	public void start() {
		this.cochineal.detachFromCactus();
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.cochineal.isInFluidType()) {
			this.cochineal.getNavigation().stop();
			if (this.cochineal.canLeap()) {
				CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
				Vec3 vec3 = this.partner.position();
				if (this.cochineal.distanceToSqr(vec3) < 36.0D && control.canReach(vec3.x, vec3.y, vec3.z)) {
					control.leapTo(vec3.x, vec3.y, vec3.z);
				} else {
					Vec3 vec31 = DefaultRandomPos.getPosTowards(this.cochineal, 12, 3, vec3, Math.PI / 6.0D);
					if (vec31 != null) {
						vec31.add(0.5D, 0.0D, 0.5D);
						if (control.canReach(vec31.x, vec31.y, vec31.z))
							control.leapTo(vec31.x, vec31.y, vec31.z);
					}
				}
			}
		}
	}

	@Override
	protected void breed() {
		this.spawnBabies((Cochineal) this.animal, this.partner);
		this.spawnBabies((Cochineal) this.partner, this.animal);
	}

	public void spawnBabies(Cochineal cochineal, Animal partner) {
		RandomSource random = cochineal.getRandom();
		int count = 1 + random.nextInt(2) + (cochineal.isSuperInLove() ? 1 : 0);
		for (int i = 0; i < count; i++) {
			cochineal.spawnChildFromBreeding((ServerLevel) this.level, partner);
		}
		cochineal.kill();
	}
}
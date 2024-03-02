package com.teamabnormals.atmospheric.common.entity.ai.goal;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import com.teamabnormals.atmospheric.common.entity.Cochineal.CochinealMoveControl;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class CochinealEatDragonFruitGoal extends Goal {
	private final Cochineal cochineal;
	private final double moveSpeed;
	private ItemEntity itemEntity;
	private int eatTime;
	private int delayCounter;

	public CochinealEatDragonFruitGoal(Cochineal cochineal, double speed) {
		this.cochineal = cochineal;
		this.moveSpeed = speed;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (this.cochineal.getAge() != 0 || !this.cochineal.canFallInLove() || this.cochineal.getRandom().nextInt(this.adjustedTickDelay(20)) != 0)
			return false;

		List<ItemEntity> list = this.cochineal.getLevel().getEntitiesOfClass(ItemEntity.class, this.cochineal.getBoundingBox().inflate(12.0D, 4.0D, 12.0D), itemEntity -> this.cochineal.isFood(itemEntity.getItem()));
		ItemEntity itementity = null;
		double d0 = Double.MAX_VALUE;

		for (ItemEntity itementity1 : list) {
			double d1 = this.cochineal.distanceToSqr(itementity1);
			if (d1 < d0) {
				d0 = d1;
				itementity = itementity1;
			}
		}

		if (itementity != null) {
			this.itemEntity = itementity;
			return true;
		}

		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return this.cochineal.getAge() == 0 && this.cochineal.canFallInLove() && this.itemEntity != null && this.itemEntity.isAlive() && this.cochineal.distanceToSqr(this.itemEntity) <= 256.0D;
	}

	@Override
	public void start() {
		this.eatTime = this.adjustedTickDelay(140);
		this.delayCounter = 0;
	}

	@Override
	public void stop() {
		this.cochineal.setEatingStack(ItemStack.EMPTY);
	}

	@Override
	public void tick() {
		if (this.cochineal.getBoundingBox().inflate(0.5D).intersects(this.itemEntity.getBoundingBox())) {
			if (this.cochineal.isOnGround() || this.cochineal.isInFluidType()) {
				this.cochineal.setEatingStack(this.itemEntity.getItem());
				this.cochineal.getLookControl().setLookAt(this.itemEntity, 10.0F, this.cochineal.getMaxHeadXRot());
				if (this.eatTime-- <= 0) {
					Player player = this.itemEntity.getThrower() != null ? this.cochineal.level.getPlayerByUUID(this.itemEntity.getThrower()) : null;
					this.cochineal.setInLove(player);
					this.cochineal.gameEvent(GameEvent.EAT);
					if (this.itemEntity.getItem().is(AtmosphericItemTags.COCHINEAL_SUPER_LOVE_FOOD))
						this.cochineal.setSuperInLove(true);

					this.itemEntity.getItem().shrink(1);
					if (this.itemEntity.getItem().isEmpty())
						this.itemEntity.discard();
				}
			}
		} else {
			this.eatTime = this.adjustedTickDelay(140);
			this.cochineal.setEatingStack(ItemStack.EMPTY);

			if (this.cochineal.isInFluidType()) {
				if (--this.delayCounter <= 0) {
					this.delayCounter = this.adjustedTickDelay(20);
					Path path = this.cochineal.getNavigation().createPath(this.itemEntity, 0);
					if (path != null) {
						this.cochineal.getNavigation().moveTo(path, this.moveSpeed);
					}
				}
			} else if (this.cochineal.canLeap()) {
				CochinealMoveControl control = (CochinealMoveControl) cochineal.getMoveControl();
				Vec3 vec3 = this.itemEntity.position();
				if (this.cochineal.distanceToSqr(vec3) < 64.0D && control.canReach(vec3.x, vec3.y, vec3.z)) {
					control.leapTo(vec3.x, vec3.y, vec3.z);
				} else {
					Vec3 vec31 = DefaultRandomPos.getPosTowards(this.cochineal, 7, 3, vec3, Math.PI / 6.0D);
					if (vec31 != null) {
						vec31.add(0.5D, 0.0D, 0.5D);
						if (control.canReach(vec31.x, vec31.y, vec31.z))
							control.leapTo(vec31.x, vec31.y, vec31.z);
					}
				}
			}
		}
	}
}
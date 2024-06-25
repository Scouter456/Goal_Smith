package com.scouter.goalsmith.data.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;

import java.util.EnumSet;

public class OfferFlowerGoalImproved extends Goal {
    private static final TargetingConditions OFFER_TARGER_CONTEXT = TargetingConditions.forNonCombat().range(6.0D);
    public static final int OFFER_TICKS = 400;
    private final Mob mob;
    private Villager villager;
    private int tick;

    public OfferFlowerGoalImproved(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!this.mob.level().isDay()) {
            return false;
        } else if (this.mob.getRandom().nextInt(8000) != 0) {
            return false;
        } else {
            this.villager = this.mob.level().getNearestEntity(Villager.class, OFFER_TARGER_CONTEXT, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
            return this.villager != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.tick > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */

    //todo maybe check for an interface?
    public void start() {
        this.tick = this.adjustedTickDelay(400);
        if(mob instanceof IronGolem golem) {
            golem.offerFlower(true);
        } else {
            this.mob.level().broadcastEntityEvent(this.mob, (byte)11);
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        if(mob instanceof IronGolem golem) {
            golem.offerFlower(false);
        } else {
            this.mob.level().broadcastEntityEvent(this.mob, (byte)34);
        }
        this.villager = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.mob.getLookControl().setLookAt(this.villager, 30.0F, 30.0F);
        --this.tick;
    }
}
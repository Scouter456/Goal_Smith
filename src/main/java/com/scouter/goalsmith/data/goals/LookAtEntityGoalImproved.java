package com.scouter.goalsmith.data.goals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class LookAtEntityGoalImproved extends Goal {
    public static final float DEFAULT_PROBABILITY = 0.02F;
    protected final Mob mob;
    @Nullable
    protected Entity lookAt;
    protected final float lookDistance;
    private int lookTime;
    protected final float probability;
    private final boolean onlyHorizontal;
    protected final TagKey<EntityType<?>> lookAtType;
    protected final TargetingConditions lookAtContext;

    public LookAtEntityGoalImproved(Mob pMob, TagKey<EntityType<?>> pLookAtType, float pLookDistance, float pProbability, boolean pOnlyHorizontal) {
        this.mob = pMob;
        this.lookAtType = pLookAtType;
        this.lookDistance = pLookDistance;
        this.probability = pProbability;
        this.onlyHorizontal = pOnlyHorizontal;
        this.setFlags(EnumSet.of(Flag.LOOK));
        this.lookAtContext = TargetingConditions.forNonCombat().range((double) pLookDistance).selector((entity) -> {
            return entity instanceof Player player && EntitySelector.notRiding(pMob).test(entity);
        });
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.mob.getRandom().nextFloat() >= this.probability) {
            return false;
        } else {
            if (this.mob.getTarget() != null) {
                this.lookAt = this.mob.getTarget();
            }


            this.lookAt = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate((double) this.lookDistance, 3.0D, (double) this.lookDistance), (entity) -> entity.getType().is(lookAtType)), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            return this.lookAt != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        if (!this.lookAt.isAlive()) {
            return false;
        } else if (this.mob.distanceToSqr(this.lookAt) > (double) (this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.lookTime > 0;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.lookAt = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.lookAt.isAlive()) {
            double d0 = this.onlyHorizontal ? this.mob.getEyeY() : this.lookAt.getEyeY();
            this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
            --this.lookTime;
        }
    }
}

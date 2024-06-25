package com.scouter.goalsmith.data.goals.target;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class NoneTameRandomTargetGoalImproved extends NearestAttackableTargetGoalImproved {
    private final TamableAnimal tamableMob;

    public NoneTameRandomTargetGoalImproved(TamableAnimal pMob, TagKey<EntityType<?>> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pTargetType, pRandomInterval, pMustSee, pMustReach, pTargetPredicate);
        this.tamableMob = pMob;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return !this.tamableMob.isTame() && super.canUse();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.targetConditions != null ? this.targetConditions.test(this.mob, this.target) : super.canContinueToUse();
    }
}

package com.scouter.goalsmith.data.goals;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class BreedGoalImproved extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight();
    protected final Animal animal;
    private final TagKey<EntityType<?>> partnerClass;
    protected final Level level;
    @Nullable
    protected Animal partner;
    private int loveTime;
    private final double speedModifier;

    public BreedGoalImproved(Animal pAnimal, double pSpeedModifier, TagKey<EntityType<?>> pPartnerClass) {
        this.animal = pAnimal;
        this.level = pAnimal.level();
        this.partnerClass = pPartnerClass;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.animal.isInLove()) {
            return false;
        } else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
    }

    @Override
    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    @Override
    public void tick() {
        this.animal.getLookControl().setLookAt(this.partner, 10.0F, (float)this.animal.getMaxHeadXRot());
        this.animal.getNavigation().moveTo(this.partner, this.speedModifier);
        this.loveTime++;
        if (this.loveTime >= this.adjustedTickDelay(60) && this.animal.distanceToSqr(this.partner) < 9.0) {
            this.breed();
        }
    }

    @Nullable
    private Animal getFreePartner() {
        List<? extends Animal> list = this.level
                .getNearbyEntities(Animal.class, PARTNER_TARGETING.selector(e -> e.getType().is(partnerClass)), this.animal, this.animal.getBoundingBox().inflate(8.0));
        double d0 = Double.MAX_VALUE;
        Animal animal = null;

        for (Animal animal1 : list) {
            if (this.animal.canMate(animal1) && this.animal.distanceToSqr(animal1) < d0) {
                animal = animal1;
                d0 = this.animal.distanceToSqr(animal1);
            }
        }

        return animal;
    }

    protected void breed() {
        this.animal.spawnChildFromBreeding((ServerLevel)this.level, this.partner);
    }
}


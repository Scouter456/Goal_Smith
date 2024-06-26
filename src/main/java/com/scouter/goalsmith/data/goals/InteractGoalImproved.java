package com.scouter.goalsmith.data.goals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class InteractGoalImproved extends LookAtEntityGoalImproved{

    public InteractGoalImproved(Mob pMob, TagKey<EntityType<?>> pLookAtType, float pLookDistance, float pProbability, boolean pOnlyHorizontal) {
        super(pMob, pLookAtType, pLookDistance, pProbability, pOnlyHorizontal);
        this.setFlags(EnumSet.of(Goal.Flag.LOOK, Goal.Flag.MOVE));
    }
}

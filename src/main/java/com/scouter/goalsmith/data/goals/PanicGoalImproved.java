package com.scouter.goalsmith.data.goals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

import java.util.function.Predicate;

public class PanicGoalImproved extends PanicGoal {
    protected final PathfinderMob mob;
    private final Predicate<Entity> predicate;
    public PanicGoalImproved(PathfinderMob pMob, double pSpeedModifier, Predicate<Entity> predicate) {
        super(pMob, pSpeedModifier);
        this.predicate = predicate;
        this.mob = pMob;
    }

    @Override
    protected boolean shouldPanic() {
        return predicate.test(mob);
    }
}

package com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;

public class RunAroundLikeCrazyGoalCodec implements GoalCodec {

    public static final Codec<RunAroundLikeCrazyGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RunAroundLikeCrazyGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RunAroundLikeCrazyGoalCodec::getSpeedModifier)
    ).apply(instance, RunAroundLikeCrazyGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public RunAroundLikeCrazyGoalCodec(int goalPriority, double speedModifier) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse) mob;
            RunAroundLikeCrazyGoal goal = new RunAroundLikeCrazyGoal(horse, speedModifier);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        } else {
            throw new IllegalArgumentException("Mob must be an AbstractHorse to use RunAroundLikeCrazyGoal");
        }
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RUN_AROUND_LIKE_CRAZY_GOAL.get();
    }

    @Override
    public String toString() {
        return "RunAroundLikeCrazyGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                '}';
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class FleeSunGoalCodec implements GoalCodec {

    public static final MapCodec<FleeSunGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FleeSunGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(FleeSunGoalCodec::getSpeedModifier)
    ).apply(instance, FleeSunGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public FleeSunGoalCodec(int goalPriority, double speedModifier) {
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
        FleeSunGoal goal = new FleeSunGoal(mob, speedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.FLEE_SUN_GOAL.get();
    }

    @Override
    public String toString() {
        return "FleeSunGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                '}';
    }
}

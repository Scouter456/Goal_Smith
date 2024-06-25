package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;

public class WaterAvoidingRandomFlyingGoalCodec implements GoalCodec {

    public static final Codec<WaterAvoidingRandomFlyingGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(WaterAvoidingRandomFlyingGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(WaterAvoidingRandomFlyingGoalCodec::getSpeedModifier)
    ).apply(instance, WaterAvoidingRandomFlyingGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public WaterAvoidingRandomFlyingGoalCodec(int goalPriority, double speedModifier) {
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
        WaterAvoidingRandomFlyingGoal goal = new WaterAvoidingRandomFlyingGoal(mob, speedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.WATER_AVOIDING_RANDOM_FLYING_GOAL.get();
    }

    @Override
    public String toString() {
        return "WaterAvoidingRandomFlyingGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                '}';
    }
}

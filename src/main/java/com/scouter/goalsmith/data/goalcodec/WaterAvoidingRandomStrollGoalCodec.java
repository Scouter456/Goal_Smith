package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class WaterAvoidingRandomStrollGoalCodec implements GoalCodec {
    public static final Codec<WaterAvoidingRandomStrollGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(WaterAvoidingRandomStrollGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(WaterAvoidingRandomStrollGoalCodec::getSpeedModifier),
            Codec.FLOAT.optionalFieldOf("probability", 0.001F).forGetter(WaterAvoidingRandomStrollGoalCodec::getProbability)
    ).apply(instance, WaterAvoidingRandomStrollGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final float probability;

    public WaterAvoidingRandomStrollGoalCodec(int goalPriority, double speedModifier, float probability) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.probability = probability;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public float getProbability() {
        return probability;
    }
    @Override
    public Goal addGoal(PathfinderMob mob) {
        WaterAvoidingRandomStrollGoal goal = new WaterAvoidingRandomStrollGoal(mob, speedModifier, probability);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.WATER_AVOIDING_RANDOM_STROLL_GOAL.get();
    }

    @Override
    public String toString() {
        return "WaterAvoidingRandomStrollGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", probability=" + probability +
                '}';
    }

}

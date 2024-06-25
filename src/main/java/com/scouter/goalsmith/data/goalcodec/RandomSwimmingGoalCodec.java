package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;

public class RandomSwimmingGoalCodec implements GoalCodec {
    public static final Codec<RandomSwimmingGoalCodec> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.INT.fieldOf("goal_priority").forGetter(codec -> codec.goalPriority),
            Codec.DOUBLE.optionalFieldOf("speed_modifier", 1.0D).forGetter(codec -> codec.speedModifier),
            Codec.INT.optionalFieldOf("interval", 120).forGetter(codec -> codec.interval)
    ).apply(builder, RandomSwimmingGoalCodec::new));

    private final double speedModifier;
    private final int goalPriority;
    private final int interval;

    public RandomSwimmingGoalCodec(int goalPriority, double speedModifier, int interval) {
        this.speedModifier = speedModifier;
        this.goalPriority = goalPriority;
        this.interval = interval;
    }
    @Override
    public Goal addGoal(PathfinderMob mob) {
        RandomSwimmingGoal goal = new RandomSwimmingGoal(mob, speedModifier, interval);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RANDOM_SWIMMING_GOAL.get();
    }

    @Override
    public String toString() {
        return "RandomSwimmingGoalCodec{" +
                "speedModifier=" + speedModifier +
                ", goalPriority=" + goalPriority +
                '}';
    }
}

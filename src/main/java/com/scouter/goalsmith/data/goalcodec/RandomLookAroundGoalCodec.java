package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class RandomLookAroundGoalCodec implements GoalCodec {

    public static final MapCodec<RandomLookAroundGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RandomLookAroundGoalCodec::getGoalPriority)
    ).apply(instance, RandomLookAroundGoalCodec::new));

    private final int goalPriority;

    public RandomLookAroundGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RandomLookAroundGoal goal = new RandomLookAroundGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.RANDOM_LOOK_AROUND_GOAL.get();
    }

    @Override
    public String toString() {
        return "RandomLookAroundGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

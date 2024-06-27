package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.WaterFleeGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class WaterFleeGoalCodec implements GoalCodec {

    public static final MapCodec<WaterFleeGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(WaterFleeGoalCodec::getGoalPriority)
    ).apply(instance, WaterFleeGoalCodec::new));

    private final int goalPriority;

    public WaterFleeGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        WaterFleeGoal goal = new WaterFleeGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.WATER_FLEE_GOAL.get();
    }

    @Override
    public String toString() {
        return "WaterFleeGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

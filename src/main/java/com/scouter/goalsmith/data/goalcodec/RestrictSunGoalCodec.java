package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;

public class RestrictSunGoalCodec implements GoalCodec {

    public static final MapCodec<RestrictSunGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RestrictSunGoalCodec::getGoalPriority)
    ).apply(instance, RestrictSunGoalCodec::new));

    private final int goalPriority;

    public RestrictSunGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RestrictSunGoal goal = new RestrictSunGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.RESTRICT_SUN_GOAL.get();
    }

    @Override
    public String toString() {
        return "RestrictSunGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class EatBlockGoalCodec implements GoalCodec {

    public static final MapCodec<EatBlockGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(EatBlockGoalCodec::getGoalPriority)
    ).apply(instance, EatBlockGoalCodec::new));

    private final int goalPriority;

    public EatBlockGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        EatBlockGoal goal = new EatBlockGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.EAT_BLOCK_GOAL.get();
    }

    @Override
    public String toString() {
        return "EatBlockGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

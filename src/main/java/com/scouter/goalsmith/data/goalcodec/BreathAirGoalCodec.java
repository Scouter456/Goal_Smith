package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class BreathAirGoalCodec implements GoalCodec {
    public static final Codec<BreathAirGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(BreathAirGoalCodec::getGoalPriority)
    ).apply(instance, BreathAirGoalCodec::new));

    private final int goalPriority;



    public BreathAirGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        BreathAirGoal goal = new BreathAirGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.BREATH_AIR_GOAL.get();
    }
}

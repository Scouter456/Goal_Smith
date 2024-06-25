package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;

public class LeapAtTargetGoalCodec implements GoalCodec {
    public static final Codec<LeapAtTargetGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LeapAtTargetGoalCodec::getGoalPriority),
            Codec.FLOAT.fieldOf("y_movement").forGetter(LeapAtTargetGoalCodec::getYd)
    ).apply(instance, LeapAtTargetGoalCodec::new));

    private final int goalPriority;
    private final float yd;

    public LeapAtTargetGoalCodec(int goalPriority, float yd) {
        this.goalPriority = goalPriority;
        this.yd = yd;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public float getYd() {
        return yd;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        LeapAtTargetGoal goal = new LeapAtTargetGoal(mob, yd);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.LEAP_AT_TARGET_GOAL.get();
    }


}
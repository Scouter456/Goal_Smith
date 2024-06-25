package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FollowBoatGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class FollowBoatGoalCodec implements GoalCodec {
    public static final Codec<FollowBoatGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowBoatGoalCodec::getGoalPriority)
    ).apply(instance, FollowBoatGoalCodec::new));

    private final int goalPriority;

    public FollowBoatGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        FollowBoatGoal goal = new FollowBoatGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_BOAT_GOAL.get();
    }
}

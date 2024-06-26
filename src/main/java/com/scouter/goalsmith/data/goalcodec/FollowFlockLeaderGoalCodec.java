package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import org.slf4j.Logger;

public class FollowFlockLeaderGoalCodec implements GoalCodec {
    public static final Codec<FollowFlockLeaderGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowFlockLeaderGoalCodec::getGoalPriority)
    ).apply(instance, FollowFlockLeaderGoalCodec::new));
    private static final Logger LOGGER = LogUtils.getLogger();

    private final int goalPriority;

    public FollowFlockLeaderGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof AbstractSchoolingFish abstractSchoolingFish) {
            FollowFlockLeaderGoal goal = new FollowFlockLeaderGoal((AbstractSchoolingFish) mob);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding FollowFlockLeaderGoalCodec to non-AbstractSchoolingFish!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_FLOCK_LEADER_GOAL.get();
    }
}

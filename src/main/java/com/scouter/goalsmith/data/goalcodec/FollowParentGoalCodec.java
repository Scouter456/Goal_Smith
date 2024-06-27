package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import org.slf4j.Logger;

public class FollowParentGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MapCodec<FollowParentGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowParentGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(FollowParentGoalCodec::getSpeedModifier)
    ).apply(instance, FollowParentGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public FollowParentGoalCodec(int goalPriority, double speedModifier) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (!(mob instanceof Animal)) {
            FollowParentGoal goal = new FollowParentGoal((Animal) mob, speedModifier);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding FollowOwnerGoal to non-animal!");
        return null;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_PARENT_GOAL.get();
    }


}
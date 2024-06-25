package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LandOnOwnersShoulderGoal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import org.slf4j.Logger;

public class LandOnOwnersShoulderGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Codec<LandOnOwnersShoulderGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LandOnOwnersShoulderGoalCodec::getGoalPriority)
    ).apply(instance, LandOnOwnersShoulderGoalCodec::new));

    private final int goalPriority;

    public LandOnOwnersShoulderGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof ShoulderRidingEntity) {
            LandOnOwnersShoulderGoal goal = new LandOnOwnersShoulderGoal((ShoulderRidingEntity) mob);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding FollowOwnerGoal to non-ShoulderRidingEntity!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.LAND_ON_OWNERS_SHOULDER_GOAL.get();
    }


}
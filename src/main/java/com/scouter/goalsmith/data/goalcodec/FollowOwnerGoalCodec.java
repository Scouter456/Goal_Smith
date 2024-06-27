package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import org.slf4j.Logger;

public class FollowOwnerGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();


    public static final MapCodec<FollowOwnerGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowOwnerGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(FollowOwnerGoalCodec::getSpeedModifier),
            Codec.FLOAT.fieldOf("start_distance").forGetter(FollowOwnerGoalCodec::getStartDistance),
            Codec.FLOAT.fieldOf("stop_distance").forGetter(FollowOwnerGoalCodec::getStopDistance)
    ).apply(instance, FollowOwnerGoalCodec::new));


    private final int goalPriority;
    private final double speedModifier;
    private final float startDistance;
    private final float stopDistance;

    public FollowOwnerGoalCodec(int goalPriority, double speedModifier, float startDistance, float stopDistance) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
    }


    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public float getStartDistance() {
        return startDistance;
    }

    public float getStopDistance() {
        return stopDistance;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            FollowOwnerGoal goal = new FollowOwnerGoal(animal, speedModifier, startDistance, stopDistance);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding FollowOwnerGoal to non-tamableanimal!");
        return null;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_OWNER_GOAL.get();
    }

}


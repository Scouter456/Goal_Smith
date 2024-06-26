package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
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


    public static final Codec<FollowOwnerGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowOwnerGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(FollowOwnerGoalCodec::getSpeedModifier),
            Codec.FLOAT.fieldOf("start_distance").forGetter(FollowOwnerGoalCodec::getStartDistance),
            Codec.FLOAT.fieldOf("stop_distance").forGetter(FollowOwnerGoalCodec::getStopDistance),
            NullableFieldCodec.makeDefaultableField("can_fly", Codec.BOOL, false).forGetter(FollowOwnerGoalCodec::isCanFly)
    ).apply(instance, FollowOwnerGoalCodec::new));


    private final int goalPriority;
    private final double speedModifier;
    private final float startDistance;
    private final float stopDistance;
    private final boolean canFly;

    public FollowOwnerGoalCodec(int goalPriority, double speedModifier, float startDistance, float stopDistance, boolean canFly) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.canFly = canFly;
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

    public boolean isCanFly() {
        return canFly;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            FollowOwnerGoal goal = new FollowOwnerGoal(animal, speedModifier, startDistance, stopDistance, canFly);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding FollowOwnerGoal to non-tamableanimal!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_OWNER_GOAL.get();
    }

}


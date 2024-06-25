package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class FollowMobGoalCodec implements GoalCodec {

    public static final Codec<FollowMobGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(FollowMobGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(FollowMobGoalCodec::getSpeedModifier),
            Codec.FLOAT.fieldOf("stop_distance").forGetter(FollowMobGoalCodec::getStopDistance),
            Codec.FLOAT.fieldOf("area_size").forGetter(FollowMobGoalCodec::getAreaSize)
    ).apply(instance, FollowMobGoalCodec::new));

    private final int goalPriority;
    private final double pSpeedModifier;
    private final float pStopDistance;
    private final float pAreaSize;

    public FollowMobGoalCodec(int goalPriority, double pSpeedModifier, float pStopDistance, float pAreaSize) {
        this.goalPriority = goalPriority;
        this.pSpeedModifier = pSpeedModifier;
        this.pStopDistance = pStopDistance;
        this.pAreaSize = pAreaSize;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return pSpeedModifier;
    }

    public float getStopDistance() {
        return pStopDistance;
    }

    public float getAreaSize() {
        return pAreaSize;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        FollowMobGoal goal = new FollowMobGoal(mob, pSpeedModifier,pStopDistance,pAreaSize);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.FOLLOW_MOB_GOAL.get();
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;

public class MoveTowardsTargetGoalCodec implements GoalCodec {
    public static final MapCodec<MoveTowardsTargetGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MoveTowardsTargetGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MoveTowardsTargetGoalCodec::getSpeedModifier),
            Codec.FLOAT.fieldOf("distance_radius").forGetter(MoveTowardsTargetGoalCodec::getWithin)
    ).apply(instance, MoveTowardsTargetGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final float within;

    public MoveTowardsTargetGoalCodec(int goalPriority, double speedModifier, float within) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.within = within;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public float getWithin() {
        return within;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        MoveTowardsTargetGoal goal = new MoveTowardsTargetGoal(mob, speedModifier, within);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.MOVE_TOWARDS_TARGET_GOAL.get();
    }


}
package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;

public class MoveTowardsRestrictionGoalCodec implements GoalCodec {
    public static final MapCodec<MoveTowardsRestrictionGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MoveTowardsRestrictionGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MoveTowardsRestrictionGoalCodec::getSpeedModifier)
    ).apply(instance, MoveTowardsRestrictionGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public MoveTowardsRestrictionGoalCodec(int goalPriority, double speedModifier) {
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
        MoveTowardsRestrictionGoal goal = new MoveTowardsRestrictionGoal(mob, speedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.MOVE_TOWARDS_RESTRICTION_GOAL.get();
    }


}
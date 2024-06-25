package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.RaiseArmAttackGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class RaiseArmAttackGoalCodec implements GoalCodec {

    public static final Codec<RaiseArmAttackGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RaiseArmAttackGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RaiseArmAttackGoalCodec::getSpeedModifier),
            Codec.BOOL.fieldOf("following_target_even_if_not_seen").forGetter(RaiseArmAttackGoalCodec::isFollowingTargetEvenIfNotSeen)
    ).apply(instance, RaiseArmAttackGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;

    public RaiseArmAttackGoalCodec(int goalPriority, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public boolean isFollowingTargetEvenIfNotSeen() {
        return followingTargetEvenIfNotSeen;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RaiseArmAttackGoal goal = new RaiseArmAttackGoal(mob, speedModifier, followingTargetEvenIfNotSeen);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RAISE_ARM_ATTACK_GOAL.get();
    }

    @Override
    public String toString() {
        return "RaiseArmAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", followingTargetEvenIfNotSeen=" + followingTargetEvenIfNotSeen +
                '}';
    }
}

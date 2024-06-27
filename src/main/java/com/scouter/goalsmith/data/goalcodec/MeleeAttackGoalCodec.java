package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class MeleeAttackGoalCodec implements GoalCodec {

    public static final MapCodec<MeleeAttackGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MeleeAttackGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MeleeAttackGoalCodec::getSpeedModifier),
            Codec.BOOL.fieldOf("following_target_even_if_not_seen").forGetter(MeleeAttackGoalCodec::isFollowingTargetEvenIfNotSeen)
    ).apply(instance, MeleeAttackGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;

    public MeleeAttackGoalCodec(int goalPriority, double speedModifier, boolean followingTargetEvenIfNotSeen) {
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
        MeleeAttackGoal goal = new MeleeAttackGoal(mob, speedModifier, followingTargetEvenIfNotSeen);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.MELEE_ATTACK_GOAL.get();
    }
    @Override
    public String toString() {
        return "MeleeAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", followingTargetEvenIfNotSeen=" + followingTargetEvenIfNotSeen +
                '}';
    }
}

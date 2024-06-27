package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class RangedBowAttackGoalCodec<T extends Mob & RangedAttackMob> implements GoalCodec {

    public static final MapCodec<RangedBowAttackGoalCodec<?>> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RangedBowAttackGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RangedBowAttackGoalCodec::getSpeedModifier),
            Codec.INT.fieldOf("attack_interval_min").forGetter(RangedBowAttackGoalCodec::getAttackIntervalMin),
            Codec.FLOAT.fieldOf("attack_radius").forGetter(RangedBowAttackGoalCodec::getAttackRadius)
    ).apply(instance, RangedBowAttackGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final int attackIntervalMin;
    private final float attackRadius;

    public RangedBowAttackGoalCodec(int goalPriority, double speedModifier, int attackIntervalMin, float attackRadius) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackRadius = attackRadius;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public int getAttackIntervalMin() {
        return attackIntervalMin;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RangedBowAttackGoal<T> goal = new RangedBowAttackGoal<T>((T) mob, speedModifier, attackIntervalMin, attackRadius);
        mob.goalSelector.addGoal(goalPriority,goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.RANGED_BOW_ATTACK_GOAL.get();
    }

    @Override
    public String toString() {
        return "RangedBowAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", attackIntervalMin=" + attackIntervalMin +
                ", attackRadius=" + attackRadius +
                '}';
    }
}
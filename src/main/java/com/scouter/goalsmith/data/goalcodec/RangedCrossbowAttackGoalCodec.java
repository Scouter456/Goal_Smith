package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;

public class RangedCrossbowAttackGoalCodec<T extends Monster & RangedAttackMob & CrossbowAttackMob> implements GoalCodec {

    public static final Codec<RangedCrossbowAttackGoalCodec<?>> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RangedCrossbowAttackGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RangedCrossbowAttackGoalCodec::getSpeedModifier),
            Codec.FLOAT.fieldOf("attack_radius").forGetter(RangedCrossbowAttackGoalCodec::getAttackRadius)
    ).apply(instance, RangedCrossbowAttackGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final float attackRadius;

    public RangedCrossbowAttackGoalCodec(int goalPriority, double speedModifier, float attackRadius) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.attackRadius = attackRadius;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof Monster && mob instanceof RangedAttackMob && mob instanceof CrossbowAttackMob) {
            @SuppressWarnings("unchecked")
            T typedMob = (T) mob;
            RangedCrossbowAttackGoal<T> goal = new RangedCrossbowAttackGoal<>(typedMob, speedModifier, attackRadius);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        } else {
            throw new IllegalArgumentException("Mob must extend Monster, RangedAttackMob, and CrossbowAttackMob to use RangedCrossbowAttackGoal");
        }
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RANGED_CROSSBOW_ATTACK_GOAL.get();
    }

    @Override
    public String toString() {
        return "RangedCrossbowAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", attackRadius=" + attackRadius +
                '}';
    }
}

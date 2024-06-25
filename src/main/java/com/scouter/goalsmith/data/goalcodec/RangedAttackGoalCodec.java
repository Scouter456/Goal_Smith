package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.slf4j.Logger;

public class RangedAttackGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Codec<RangedAttackGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RangedAttackGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RangedAttackGoalCodec::getSpeedModifier),
            Codec.INT.fieldOf("attack_interval_min").forGetter(RangedAttackGoalCodec::getAttackIntervalMin),
            Codec.INT.fieldOf("attack_interval_max").forGetter(RangedAttackGoalCodec::getAttackIntervalMax),
            Codec.FLOAT.fieldOf("attack_radius").forGetter(RangedAttackGoalCodec::getAttackRadius)
    ).apply(instance, RangedAttackGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;

    public RangedAttackGoalCodec(int goalPriority, double speedModifier, int attackIntervalMin, int attackIntervalMax, float attackRadius) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackIntervalMax = attackIntervalMax;
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

    public int getAttackIntervalMax() {
        return attackIntervalMax;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof RangedAttackMob rangedAttackMob) {
            RangedAttackGoal goal = new RangedAttackGoal(rangedAttackMob, speedModifier, attackIntervalMin, attackIntervalMax, attackRadius);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding RangedAttackGoal to non-RangedAttackMob!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RANGED_ATTACK_GOAL.get();
    }

    @Override
    public String toString() {
        return "RangedAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", attackIntervalMin=" + attackIntervalMin +
                ", attackIntervalMax=" + attackIntervalMax +
                ", attackRadius=" + attackRadius +
                '}';
    }
}

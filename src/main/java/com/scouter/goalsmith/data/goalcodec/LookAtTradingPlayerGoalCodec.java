package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.slf4j.Logger;

public class LookAtTradingPlayerGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();


    private final int goalPriority;
    private final float maxDistance;

    public LookAtTradingPlayerGoalCodec(int goalPriority, float maxDistance) {
        this.goalPriority = goalPriority;
        this.maxDistance = maxDistance;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public float getMaxDistance() {
        return maxDistance;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof AbstractVillager villager) {
            LookAtTradingPlayerGoal goal = new LookAtTradingPlayerGoal(villager);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding LookAtTradingPlayerGoal to non-AbstractVillager!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.LOOK_AT_TRADING_PLAYER_GOAL.get();
    }

    public static final Codec<LookAtTradingPlayerGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LookAtTradingPlayerGoalCodec::getGoalPriority),
            Codec.FLOAT.fieldOf("max_distance").forGetter(LookAtTradingPlayerGoalCodec::getMaxDistance)
    ).apply(instance, LookAtTradingPlayerGoalCodec::new));
}
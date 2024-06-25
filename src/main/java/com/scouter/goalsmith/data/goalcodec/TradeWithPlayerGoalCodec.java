package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.slf4j.Logger;

public class TradeWithPlayerGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Codec<TradeWithPlayerGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(TradeWithPlayerGoalCodec::getGoalPriority)
    ).apply(instance, TradeWithPlayerGoalCodec::new));
    private final int goalPriority;

    public TradeWithPlayerGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof AbstractVillager villager) {
            TradeWithPlayerGoal goal = new TradeWithPlayerGoal(villager);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding BreedGoal to non-animal!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.TRADE_WITH_PLAYER_GOAL.get();
    }


}
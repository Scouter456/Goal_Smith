package com.scouter.goalsmith.data.goalcodec.entityspecific.llama;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LlamaFollowCaravanGoal;
import net.minecraft.world.entity.animal.horse.Llama;
import org.slf4j.Logger;

public class LlamaFollowCaravanGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final MapCodec<LlamaFollowCaravanGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LlamaFollowCaravanGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(LlamaFollowCaravanGoalCodec::getSpeedModifier)
    ).apply(instance, LlamaFollowCaravanGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public LlamaFollowCaravanGoalCodec(int goalPriority, double speedModifier) {
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
        if (mob instanceof Llama) {
            LlamaFollowCaravanGoal goal = new LlamaFollowCaravanGoal((Llama) mob, speedModifier);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }


        LOGGER.error("Unsupported Operation, Tried adding LlamaFollowCaravanGoal to non-Llama!");
        return null;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.LLAMA_FOLLOW_CARAVAN_GOAL.get();
    }


}
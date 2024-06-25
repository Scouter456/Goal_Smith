package com.scouter.goalsmith.data.goalcodec.entityspecific.creeper;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import org.slf4j.Logger;

public class SwellGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    private final int goalPriority;

    public SwellGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof Creeper creeper) {
            SwellGoal goal = new SwellGoal(creeper);
            creeper.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding SwellGoal to non-Creeper!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.SWELL_GOAL.get();
    }

    public static final Codec<SwellGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(SwellGoalCodec::getGoalPriority)
    ).apply(instance, SwellGoalCodec::new));
}
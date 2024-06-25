package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.StrollThroughVillageGoal;

public class StrollThroughVillageGoalCodec implements GoalCodec {
    public static final Codec<StrollThroughVillageGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(StrollThroughVillageGoalCodec::getGoalPriority),
            Codec.INT.fieldOf("interval").forGetter(StrollThroughVillageGoalCodec::getInterval)
    ).apply(instance, StrollThroughVillageGoalCodec::new));
    private final int goalPriority;
    private final int interval;

    public StrollThroughVillageGoalCodec(int goalPriority, int interval) {
        this.goalPriority = goalPriority;
        this.interval = interval;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        StrollThroughVillageGoal goal = new StrollThroughVillageGoal(mob, interval);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.STROLL_THROUGH_VILLAGE_GOAL.get();
    }


}
package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.JumpWaterGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class JumpWaterGoalCodec implements GoalCodec {
    public static final MapCodec<JumpWaterGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(JumpWaterGoalCodec::getGoalPriority),
            Codec.INT.fieldOf("interval").forGetter(JumpWaterGoalCodec::getInterval)
    ).apply(instance, JumpWaterGoalCodec::new));

    private final int goalPriority;
    private final int interval;

    public JumpWaterGoalCodec(int goalPriority, int pInterval) {
        this.goalPriority = goalPriority;
        this.interval = pInterval;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        JumpWaterGoal goal = new JumpWaterGoal(mob, interval);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.JUMP_WATER_GOAL.get();
    }
}

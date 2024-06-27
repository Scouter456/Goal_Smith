package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GolemRandomStrollInVillageGoal;

public class RandomStrollInVillageGoalCodec implements GoalCodec {


    public static final MapCodec<RandomStrollInVillageGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RandomStrollInVillageGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RandomStrollInVillageGoalCodec::getSpeedModifier)
    ).apply(instance, RandomStrollInVillageGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public RandomStrollInVillageGoalCodec(int goalPriority, double speedModifier) {
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
        GolemRandomStrollInVillageGoal goal = new GolemRandomStrollInVillageGoal(mob, speedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.RANDOM_STROLL_IN_VILLAGE_GOAL.get();
    }

}
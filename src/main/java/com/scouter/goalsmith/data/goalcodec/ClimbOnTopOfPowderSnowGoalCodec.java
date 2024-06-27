package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.ClimbOnTopOfPowderSnowGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class ClimbOnTopOfPowderSnowGoalCodec implements GoalCodec {
    public static final MapCodec<ClimbOnTopOfPowderSnowGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(ClimbOnTopOfPowderSnowGoalCodec::getGoalPriority)
    ).apply(instance, ClimbOnTopOfPowderSnowGoalCodec::new));

    private final int goalPriority;

    public ClimbOnTopOfPowderSnowGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        ClimbOnTopOfPowderSnowGoal goal =new ClimbOnTopOfPowderSnowGoal(mob, mob.level());
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.CLIMB_ON_TOP_OF_POWDER_SNOW_GOAL.get();
    }
}

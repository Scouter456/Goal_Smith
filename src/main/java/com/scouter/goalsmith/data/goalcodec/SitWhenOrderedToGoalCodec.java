package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import org.slf4j.Logger;

public class SitWhenOrderedToGoalCodec implements GoalCodec {

    public static final MapCodec<SitWhenOrderedToGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(SitWhenOrderedToGoalCodec::getGoalPriority)
    ).apply(instance, SitWhenOrderedToGoalCodec::new));
    private static final Logger LOGGER = LogUtils.getLogger();

    private final int goalPriority;

    public SitWhenOrderedToGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof TamableAnimal) {
            SitWhenOrderedToGoal goal = new SitWhenOrderedToGoal((TamableAnimal) mob);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding BreedGoal to non-TamableAnimal!");
        return null;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.SIT_WHEN_ORDERED_GOAL.get();
    }

}

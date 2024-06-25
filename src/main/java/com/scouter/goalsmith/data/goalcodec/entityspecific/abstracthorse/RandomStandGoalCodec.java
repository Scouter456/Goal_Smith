package com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStandGoal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.slf4j.Logger;

public class RandomStandGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Codec<RandomStandGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RandomStandGoalCodec::getGoalPriority)
    ).apply(instance, RandomStandGoalCodec::new));

    private final int goalPriority;

    public RandomStandGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse) mob;
            RandomStandGoal goal = new RandomStandGoal(horse);
            horse.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding RandomStandGoal to non-AbstractHorse!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RANDOM_STAND_GOAL.get();
    }

    @Override
    public String toString() {
        return "RandomStandGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

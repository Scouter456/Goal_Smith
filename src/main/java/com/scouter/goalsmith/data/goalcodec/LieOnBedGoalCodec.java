package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.LieOnBedGoalImproved;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import org.slf4j.Logger;

public class LieOnBedGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Codec<LieOnBedGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LieOnBedGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(LieOnBedGoalCodec::getSpeedModifier),
            Codec.INT.fieldOf("search_range").forGetter(LieOnBedGoalCodec::getSearchRange)
            ).apply(instance, LieOnBedGoalCodec::new));

    private final int goalPriority;
    private final int searchRange;
    private final double speedModifier;

    public LieOnBedGoalCodec(int goalPriority,double pSpeedModifier, int searchRange) {
        this.goalPriority = goalPriority;
        this.searchRange = searchRange;
        this.speedModifier = pSpeedModifier;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }
    private  int getSearchRange() {
        return searchRange;
    }
    private double getSpeedModifier() {
        return speedModifier;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            LieOnBedGoalImproved goal = new LieOnBedGoalImproved(animal, speedModifier, searchRange);
            animal.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }
        LOGGER.error("Unsupported Operation, Tried adding LieOnBedGoal to non-tamableanimal!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.LIE_ON_BED_GOAL.get();
    }
}

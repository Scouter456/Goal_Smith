package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.SitOnBlockGoalImproved;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import org.slf4j.Logger;

public class SitOnBlockGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();


    public static final Codec<SitOnBlockGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(SitOnBlockGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(SitOnBlockGoalCodec::getSpeedModifier)
    ).apply(instance, SitOnBlockGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;

    public SitOnBlockGoalCodec(int goalPriority,double pSpeedModifier) {
        this.goalPriority = goalPriority;
        this.speedModifier = pSpeedModifier;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }

    private double getSpeedModifier() {
        return speedModifier;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            SitOnBlockGoalImproved goal =new SitOnBlockGoalImproved(animal, speedModifier);
            animal.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }

        LOGGER.error("Unsupported Operation, Tried adding SitOnBlockGoal to non-tamableanimal!");
        return null;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.SIT_ON_BLOCK_GOAL.get();
    }
}

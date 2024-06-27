package com.scouter.goalsmith.data.goalcodec.targetgoalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.TargetGoalCodec;
import com.scouter.goalsmith.data.goals.DefendVillageTargetGoalImproved;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class DefendVillageTargetGoalCodec implements TargetGoalCodec {

    public static final MapCodec<DefendVillageTargetGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(DefendVillageTargetGoalCodec::getGoalPriority)
    ).apply(instance, DefendVillageTargetGoalCodec::new));

    private final int goalPriority;

    public DefendVillageTargetGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }


    @Override
    public Goal addTargetGoal(PathfinderMob mob) {
        DefendVillageTargetGoalImproved goal = new DefendVillageTargetGoalImproved(mob);
        mob.targetSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends TargetGoalCodec> codec() {
        return GoalRegistry.DEFEND_VILLAGE_TARGET_GOAL.get();
    }

    @Override
    public String toString() {
        return "DefendVillageTargetGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

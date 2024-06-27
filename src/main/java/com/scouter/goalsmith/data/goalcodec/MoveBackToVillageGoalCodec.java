package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveBackToVillageGoal;

public class MoveBackToVillageGoalCodec implements GoalCodec {
    public static final MapCodec<MoveBackToVillageGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MoveBackToVillageGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MoveBackToVillageGoalCodec::getSpeedModifier),
            Codec.BOOL.fieldOf("check_no_action_time").forGetter(MoveBackToVillageGoalCodec::isCheckNoActionTime)
    ).apply(instance, MoveBackToVillageGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final boolean checkNoActionTime;

    public MoveBackToVillageGoalCodec(int goalPriority, double speedModifier, boolean checkNoActionTime) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.checkNoActionTime = checkNoActionTime;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public boolean isCheckNoActionTime() {
        return checkNoActionTime;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        MoveBackToVillageGoal goal = new MoveBackToVillageGoal(mob, speedModifier, checkNoActionTime);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.MOVE_BACK_TO_VILLAGE_GOAL.get();
    }


}
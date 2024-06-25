package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;

public class OpenDoorGoalCodec implements GoalCodec {

    public static final Codec<OpenDoorGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(OpenDoorGoalCodec::getGoalPriority),
            Codec.BOOL.fieldOf("close_door").forGetter(OpenDoorGoalCodec::getCloseDoor)
    ).apply(instance, OpenDoorGoalCodec::new));

    private final int goalPriority;
    private final boolean closeDoor;

    public OpenDoorGoalCodec(int goalPriority, boolean closeDoor) {
        this.goalPriority = goalPriority;
        this.closeDoor = closeDoor;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public boolean getCloseDoor() {
        return closeDoor;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        OpenDoorGoal goal = new OpenDoorGoal(mob, closeDoor);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.OPEN_DOOR_GOAL.get();
    }

    @Override
    public String toString() {
        return "OpenDoorGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", closeDoor=" + closeDoor +
                '}';
    }
}
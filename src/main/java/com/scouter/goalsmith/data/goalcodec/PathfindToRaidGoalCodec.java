package com.scouter.goalsmith.data.goalcodec;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PathfindToRaidGoal;
import net.minecraft.world.entity.raid.Raider;
import org.slf4j.Logger;
public class PathfindToRaidGoalCodec implements GoalCodec {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final MapCodec<PathfindToRaidGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(PathfindToRaidGoalCodec::getGoalPriority)
    ).apply(instance, PathfindToRaidGoalCodec::new));

    private final int goalPriority;

    public PathfindToRaidGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        if (mob instanceof Raider) {
            PathfindToRaidGoal goal = new PathfindToRaidGoal((Raider) mob);
            mob.goalSelector.addGoal(goalPriority, goal);
            return goal;
        }


        LOGGER.error("Unsupported Operation, Tried adding PathfindToRaidGoal to non-Raider!");
        return null;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.PATHFIND_TO_RAID_GOAL.get();
    }

    @Override
    public String toString() {
        return "PathfindToRaidGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}
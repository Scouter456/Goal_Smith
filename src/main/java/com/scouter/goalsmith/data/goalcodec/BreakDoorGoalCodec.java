package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.Goal;

public class BreakDoorGoalCodec implements GoalCodec {

    public static final MapCodec<BreakDoorGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(BreakDoorGoalCodec::getGoalPriority),
            Codec.INT.fieldOf("door_break_time").forGetter(BreakDoorGoalCodec::getDoorBreakTime),
            PredicateCodec.DIRECT_CODEC.fieldOf("difficulty_predicate").forGetter(e -> e.predicateCodec)
    ).apply(instance, (integer, integer2, predicateCodec1) -> new BreakDoorGoalCodec(integer, integer2, (PredicateCodec<Difficulty>) predicateCodec1)));

    private final int goalPriority;
    private final int doorBreakTime;
    private final PredicateCodec<Difficulty> predicateCodec;

    public BreakDoorGoalCodec(int goalPriority, int doorBreakTime,  PredicateCodec<Difficulty> predicateCodec) {
        this.goalPriority = goalPriority;
        this.doorBreakTime = doorBreakTime;
        this.predicateCodec = predicateCodec;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public int getDoorBreakTime() {
        return doorBreakTime;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        BreakDoorGoal goal = new BreakDoorGoal(mob, doorBreakTime, predicateCodec.getPredicate());
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.BREAK_DOOR_GOAL.get();
    }

    @Override
    public String toString() {
        return "BreakDoorGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", doorBreakTime=" + doorBreakTime +
                ", validDifficulties=" + predicateCodec.getPredicate() +
                '}';
    }
}
package com.scouter.goalsmith.data.goalcodec.entityspecific.ocelot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;

public class OcelotAttackGoalCodec implements GoalCodec {

    public static final MapCodec<OcelotAttackGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(OcelotAttackGoalCodec::getGoalPriority)
    ).apply(instance, OcelotAttackGoalCodec::new));

    private final int goalPriority;

    public OcelotAttackGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        OcelotAttackGoal goal = new OcelotAttackGoal(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.OCELOT_ATTACK_GOAL.get();
    }

    @Override
    public String toString() {
        return "OcelotAttackGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

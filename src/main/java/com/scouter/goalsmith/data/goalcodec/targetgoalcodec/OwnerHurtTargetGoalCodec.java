package com.scouter.goalsmith.data.goalcodec.targetgoalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.TargetGoalCodec;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;

public class OwnerHurtTargetGoalCodec implements TargetGoalCodec {

    public static final Codec<OwnerHurtTargetGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(OwnerHurtTargetGoalCodec::getGoalPriority)
    ).apply(instance, OwnerHurtTargetGoalCodec::new));

    private final int goalPriority;

    public OwnerHurtTargetGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }


    @Override
    public Goal addTargetGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            OwnerHurtTargetGoal goal = new OwnerHurtTargetGoal(animal);
            mob.targetSelector.addGoal(goalPriority, goal);
            return goal;
        }
        throw new RuntimeException("Tried adding OwnerHurtTargetGoal to non-TamableAnimal");
    }

    @Override
    public Codec<? extends TargetGoalCodec> codec() {
        return GoalRegistry.OWNER_HURT_TARGET_GOAL.get();
    }

    @Override
    public String toString() {
        return "OwnerHurtByTargetGoalCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}
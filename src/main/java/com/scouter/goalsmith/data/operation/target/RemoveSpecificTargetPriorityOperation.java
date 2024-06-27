package com.scouter.goalsmith.data.operation.target;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import com.scouter.goalsmith.data.TargetGoalOperation;
import net.minecraft.world.entity.PathfinderMob;

public record RemoveSpecificTargetPriorityOperation(int toRemove) implements TargetGoalOperation {


    public static final MapCodec<RemoveSpecificTargetPriorityOperation> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("target_goal_priority_to_remove").forGetter(RemoveSpecificTargetPriorityOperation::toRemove)
            ).apply(instance, RemoveSpecificTargetPriorityOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        synchronized (mob.targetSelector) {
            mob.targetSelector.getAvailableGoals().stream()
                    .filter(goal -> goal != null &&
                            goal.getPriority() == toRemove)
                    .toList()
                    .forEach(goal -> mob.targetSelector.removeGoal(goal.getGoal()));
        }
    }

    @Override
    public MapCodec<? extends TargetGoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_SPECIFIC_TARGET_PRIORITY.get();
    }
}

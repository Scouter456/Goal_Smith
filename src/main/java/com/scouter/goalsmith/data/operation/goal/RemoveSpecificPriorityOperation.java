package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;

public record RemoveSpecificPriorityOperation(int toRemove) implements GoalOperation {


    public static final MapCodec<RemoveSpecificPriorityOperation> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("goal_priority_to_remove").forGetter(RemoveSpecificPriorityOperation::toRemove)
            ).apply(instance, RemoveSpecificPriorityOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        synchronized (mob.goalSelector) {
            mob.goalSelector.getAvailableGoals().stream()
                    .filter(goal -> goal != null &&
                            goal.getPriority() == toRemove)
                    .toList()
                    .forEach(goal -> mob.goalSelector.removeGoal(goal.getGoal()));
        }
    }

    @Override
    public MapCodec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_SPECIFIC_PRIORITY.get();
    }

}

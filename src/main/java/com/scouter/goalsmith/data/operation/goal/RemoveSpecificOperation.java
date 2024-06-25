package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalMappings;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public record RemoveSpecificOperation(ReplacementGoal toRemove) implements GoalOperation {

    private static final Codec<ReplacementGoal> REPLACEMENT_GOAL_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    NullableFieldCodec.makeDefaultableField("goal_priority", Codec.INT, -1).forGetter(ReplacementGoal::priority),
                    GoalMappings.CODEC.fieldOf("goal").forGetter(ReplacementGoal::goal)
            ).apply(instance, ReplacementGoal::new)
    );

    public static final Codec<RemoveSpecificOperation> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    REPLACEMENT_GOAL_CODEC.fieldOf("goal_to_remove").forGetter(RemoveSpecificOperation::toRemove)
            ).apply(instance, RemoveSpecificOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        synchronized (mob.goalSelector) {
            mob.goalSelector.getAvailableGoals().stream()
                    .filter(goal -> goal != null &&
                            goal.getPriority() == toRemove.priority &&
                            goal.getGoal() != null &&
                            goal.getGoal().getClass().equals(toRemove.goal))
                    .toList()
                    .forEach(goal -> mob.goalSelector.removeGoal(goal.getGoal()));
        }
    }

    @Override
    public Codec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_SPECIFIC_GOAL.get();
    }

    public record ReplacementGoal(int priority, Class<? extends Goal> goal){};
}

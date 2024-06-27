package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalMappings;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public record ReplaceOperation(ReplacementGoal toReplace, GoalCodec replacement) implements GoalOperation {

    private static final MapCodec<ReplacementGoal> REPLACEMENT_GOAL_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    NullableFieldCodec.makeDefaultableField("goal_priority", Codec.INT, -1).forGetter(ReplacementGoal::priority),
                    GoalMappings.CODEC.fieldOf("goal").forGetter(ReplacementGoal::goal)
            ).apply(instance, ReplacementGoal::new)
    );

    public static final MapCodec<ReplaceOperation> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    REPLACEMENT_GOAL_CODEC.fieldOf("goal_to_replace").forGetter(ReplaceOperation::toReplace),
                    GoalCodec.DIRECT_CODEC.fieldOf("replacement_goal").forGetter(ReplaceOperation::replacement)
            ).apply(instance, ReplaceOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        synchronized (mob.goalSelector) {
            mob.goalSelector.getAvailableGoals().stream()
                    .filter(goal -> goal != null &&
                            goal.getPriority() == toReplace.priority &&
                            goal.getGoal() != null &&
                            goal.getGoal().getClass().equals(toReplace.goal))
                    .toList()
                    .forEach(goal -> mob.goalSelector.removeGoal(goal.getGoal()));
        }
        replacement.addGoal(mob);
    }

    @Override
    public MapCodec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.REPLACE_GOAL.get();
    }

    public record ReplacementGoal(int priority, Class<? extends Goal> goal){};
}

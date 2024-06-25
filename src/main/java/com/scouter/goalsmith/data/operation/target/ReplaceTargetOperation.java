package com.scouter.goalsmith.data.operation.target;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import com.scouter.goalsmith.data.TargetGoalCodec;
import com.scouter.goalsmith.data.TargetGoalMappings;
import com.scouter.goalsmith.data.TargetGoalOperation;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public record ReplaceTargetOperation(ReplacementGoal toReplace, TargetGoalCodec replacement) implements TargetGoalOperation {

    private static final Codec<ReplacementGoal> REPLACEMENT_GOAL_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    NullableFieldCodec.makeDefaultableField("goal_priority", Codec.INT, -1).forGetter(ReplacementGoal::priority),
                    TargetGoalMappings.CODEC.fieldOf("target_goal").forGetter(ReplacementGoal::goal)
            ).apply(instance, ReplacementGoal::new)
    );

    public static final Codec<ReplaceTargetOperation> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    REPLACEMENT_GOAL_CODEC.fieldOf("target_goal_to_replace").forGetter(ReplaceTargetOperation::toReplace),
                    TargetGoalCodec.DIRECT_CODEC.fieldOf("replacement_target_goal").forGetter(ReplaceTargetOperation::replacement)
            ).apply(instance, ReplaceTargetOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        synchronized (mob.targetSelector) {
            mob.targetSelector.getAvailableGoals().stream()
                    .filter(goal -> goal != null &&
                            goal.getPriority() == toReplace.priority &&
                            goal.getGoal() != null &&
                            goal.getGoal().getClass().equals(toReplace.goal))
                    .toList()
                    .forEach(goal -> mob.targetSelector.removeGoal(goal.getGoal()));
        }
        replacement.addTargetGoal(mob);
    }

    @Override
    public Codec<? extends TargetGoalOperation> codec() {
        return GoalOperationRegistry.REPLACE_TARGET_GOAL.get();
    }

    public record ReplacementGoal(int priority, Class<? extends Goal> goal){};
}

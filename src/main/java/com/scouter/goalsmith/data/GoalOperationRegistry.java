package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.operation.goal.*;
import com.scouter.goalsmith.data.operation.target.*;
import net.minecraft.core.Registry;

import static com.scouter.goalsmith.Goalsmith.prefix;

public class GoalOperationRegistry {


    public static final Codec<AddOperation> ADD_GOAL = registerGoalOperation("add_goal_operation", AddOperation.CODEC);
    public static final Codec<RemoveAllOperation> REMOVE_ALL_GOALS = registerGoalOperation("remove_all_goal_operation", RemoveAllOperation.CODEC);
    public static final Codec<RemoveSpecificOperation> REMOVE_SPECIFIC_GOAL = registerGoalOperation("remove_specific_goal_operation", RemoveSpecificOperation.CODEC);
    public static final Codec<RemoveSpecificPriorityOperation> REMOVE_SPECIFIC_PRIORITY = registerGoalOperation("remove_specific_priority_operation", RemoveSpecificPriorityOperation.CODEC);

    public static final Codec<ReplaceOperation> REPLACE_GOAL = registerGoalOperation("replace_goal_operation", ReplaceOperation.CODEC);

    public static final Codec<AddTargetOperation> ADD_TARGET_GOAL = registerTargetGoalOperation("add_target_goal_operation", AddTargetOperation.CODEC);
    public static final Codec<RemoveAllTargetOperation> REMOVE_ALL_TARGET_GOALS = registerTargetGoalOperation("remove_all_target_goal_operation", RemoveAllTargetOperation.CODEC);
    public static final Codec<RemoveSpecificTargetOperation> REMOVE_SPECIFIC_TARGET_GOAL = registerTargetGoalOperation("remove_specific_target_goal_operation", RemoveSpecificTargetOperation.CODEC);
    public static final Codec<RemoveSpecificTargetPriorityOperation> REMOVE_SPECIFIC_TARGET_PRIORITY = registerTargetGoalOperation("remove_specific_target_priority_operation", RemoveSpecificTargetPriorityOperation.CODEC);
    public static final Codec<ReplaceTargetOperation> REPLACE_TARGET_GOAL = registerTargetGoalOperation("replace_target_goal_operation", ReplaceTargetOperation.CODEC);


    private static <T extends GoalOperation> Codec<T> registerGoalOperation(String name, Codec<T> type) {
        return Registry.register(GSRegistries.GOAL_OPERATION_SERIALIZER, prefix(name), type);
    }

    private static <T extends TargetGoalOperation> Codec<T> registerTargetGoalOperation(String name, Codec<T> type) {
        return Registry.register(GSRegistries.TARGET_GOAL_OPERATION_SERIALIZER, prefix(name), type);
    }

    public static void register()
    {
    }
}

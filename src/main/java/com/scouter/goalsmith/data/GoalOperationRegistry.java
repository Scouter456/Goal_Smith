package com.scouter.goalsmith.data;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.operation.goal.*;
import com.scouter.goalsmith.data.operation.target.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.scouter.goalsmith.GoalSmith.MODID;

public class GoalOperationRegistry {
    public static final DeferredRegister<MapCodec<? extends GoalOperation>> GOAL_OPERATION = DeferredRegister.create(PMRegistries.Keys.GOAL_OPERATION_SERIALIZERS, MODID);
    public static final DeferredRegister<MapCodec<? extends TargetGoalOperation>> TARGET_GOAL_OPERATION = DeferredRegister.create(PMRegistries.Keys.TARGET_GOAL_OPERATION_SERIALIZERS, MODID);



    public static final DeferredHolder<MapCodec<? extends GoalOperation>, MapCodec<AddOperation>> ADD_GOAL = GOAL_OPERATION.register("add_goal_operation", () -> AddOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalOperation>, MapCodec<RemoveAllOperation>> REMOVE_ALL_GOALS = GOAL_OPERATION.register("remove_all_goal_operation", () -> RemoveAllOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalOperation>, MapCodec<RemoveSpecificOperation>> REMOVE_SPECIFIC_GOAL = GOAL_OPERATION.register("remove_specific_goal_operation", () -> RemoveSpecificOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalOperation>, MapCodec<RemoveSpecificPriorityOperation>> REMOVE_SPECIFIC_PRIORITY = GOAL_OPERATION.register("remove_specific_priority_operation", () -> RemoveSpecificPriorityOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalOperation>, MapCodec<ReplaceOperation>> REPLACE_GOAL = GOAL_OPERATION.register("replace_goal_operation", () -> ReplaceOperation.CODEC);

    public static final DeferredHolder<MapCodec<? extends TargetGoalOperation>, MapCodec<AddTargetOperation>> ADD_TARGET_GOAL = TARGET_GOAL_OPERATION.register("add_target_goal_operation", () -> AddTargetOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalOperation>, MapCodec<RemoveAllTargetOperation>> REMOVE_ALL_TARGET_GOALS = TARGET_GOAL_OPERATION.register("remove_all_target_goal_operation", () -> RemoveAllTargetOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalOperation>, MapCodec<RemoveSpecificTargetOperation>> REMOVE_SPECIFIC_TARGET_GOAL = TARGET_GOAL_OPERATION.register("remove_specific_target_goal_operation", () -> RemoveSpecificTargetOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalOperation>, MapCodec<RemoveSpecificTargetPriorityOperation>> REMOVE_SPECIFIC_TARGET_PRIORITY = TARGET_GOAL_OPERATION.register("remove_specific_target_priority_operation", () -> RemoveSpecificTargetPriorityOperation.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalOperation>, MapCodec<ReplaceTargetOperation>> REPLACE_TARGET_GOAL = TARGET_GOAL_OPERATION.register("replace_target_goal_operation", () -> ReplaceTargetOperation.CODEC);

}

package com.scouter.goalsmith.data;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.operation.goal.RemoveAllOperation;
import com.scouter.goalsmith.data.operation.goal.RemoveSpecificOperation;
import com.scouter.goalsmith.data.operation.goal.RemoveSpecificPriorityOperation;
import com.scouter.goalsmith.data.operation.target.RemoveAllTargetOperation;
import com.scouter.goalsmith.data.operation.target.RemoveSpecificTargetOperation;
import com.scouter.goalsmith.data.operation.target.RemoveSpecificTargetPriorityOperation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;

import java.util.Collections;
import java.util.List;

public class GoalData {
    public static final Codec<GoalData> TARGET_ENTITY_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    EntityTargetType.DIRECT_CODEC.fieldOf("target_type").forGetter(GoalData::getEntityTargetType),
                    NullableFieldCodec.makeDefaultableField("goal_operations", GoalOperation.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::getGoalOperation),
                    NullableFieldCodec.makeDefaultableField("target_goal_operations", TargetGoalOperation.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::getTargetGoalOperation),
                    NullableFieldCodec.makeDefaultableField("attributes_additions", AttributesAdditions.CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::getAttributeAdditions)
            ).apply(instance, GoalData::new)
    );


    private final List<GoalOperation> goalOperation;
    private final List<TargetGoalOperation> targetGoalOperation;
    private final List<AttributesAdditions> attributeAdditions;
    private final EntityTargetType entityTargetType;
    public GoalData(EntityTargetType targetType,
                    List<GoalOperation> goalOperation,
                    List<TargetGoalOperation> targetGoalOperation,
                    List<AttributesAdditions> attributeAdditions) {


        this.entityTargetType = targetType;
        List<GoalOperation> goalOperations = goalOperation.stream()
                .sorted((op1, op2) -> {
                    boolean isOp1Removal = (op1 instanceof RemoveSpecificOperation || op1 instanceof RemoveSpecificPriorityOperation || op1 instanceof RemoveAllOperation);
                    boolean isOp2Removal = (op2 instanceof RemoveSpecificOperation || op2 instanceof RemoveSpecificPriorityOperation || op2 instanceof RemoveAllOperation);
                    return Boolean.compare(isOp2Removal, isOp1Removal);
                })
                .toList();

        List<TargetGoalOperation> targetGoalOperations = targetGoalOperation.stream()
                .sorted((op1, op2) -> {
                    boolean isOp1Removal = (op1 instanceof RemoveSpecificTargetOperation || op1 instanceof RemoveSpecificTargetPriorityOperation || op1 instanceof RemoveAllTargetOperation);
                    boolean isOp2Removal = (op2 instanceof RemoveSpecificTargetOperation || op2 instanceof RemoveSpecificTargetPriorityOperation || op2 instanceof RemoveAllTargetOperation);
                    return Boolean.compare(isOp2Removal, isOp1Removal);
                })
                .toList();



        this.goalOperation = goalOperations;
        this.targetGoalOperation = targetGoalOperations;
        this.attributeAdditions = attributeAdditions;
    }

    public void performOperations(PathfinderMob mob) {
        for (GoalOperation goalOperation : goalOperation) {
            goalOperation.performOperation(mob);
        }


        for(TargetGoalOperation targetGoalOperation : targetGoalOperation) {
            targetGoalOperation.performOperation(mob);
        }

        for(AttributeAdditions attributeAdditions : attributeAdditions) {
            attributeAdditions.performAdditions(mob);
        }

    }

    public EntityTargetType getEntityTargetType() {
        return entityTargetType;
    }


    public List<GoalOperation> getGoalOperation() {
        return goalOperation;
    }

    public List<TargetGoalOperation> getTargetGoalOperation() {
        return targetGoalOperation;
    }

    public List<AttributesAdditions> getAttributeAdditions() {
        return attributeAdditions;
    }
}


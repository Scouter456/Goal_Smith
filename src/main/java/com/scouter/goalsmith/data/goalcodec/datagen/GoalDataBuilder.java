package com.scouter.goalsmith.data.goalcodec.datagen;

import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.data.operation.goal.*;
import com.scouter.goalsmith.data.operation.target.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;

import java.util.ArrayList;
import java.util.List;

public class GoalDataBuilder {
    private ResourceLocation targetEntity;
    private final List<GoalOperation> goalOperations = new ArrayList<>();
    private final List<TargetGoalOperation> targetGoalOperations = new ArrayList<>();
    private final List<AttributesAdditions> attributeAdditions = new ArrayList<>();


    public GoalDataBuilder(EntityType<?> targetEntity) {
        String ns = targetEntity.getDefaultLootTable().location().getNamespace();
        String name = targetEntity.getDefaultLootTable().location().getPath().split("/")[1];
        this.targetEntity = ResourceLocation.fromNamespaceAndPath(ns, name);
    }

    public GoalDataBuilder(ResourceLocation targetEntity) {
        this.targetEntity = targetEntity;
    }

    public GoalDataBuilder addGoalOperation(GoalOperation operation) {
        goalOperations.add(operation);
        return this;
    }

    public GoalDataBuilder addTargetGoalOperation(TargetGoalOperation operation) {
        targetGoalOperations.add(operation);
        return this;
    }

    public GoalDataBuilder withAttributeAdditions(AttributesAdditions.AttributesMap... attribute) {
        attributeAdditions.add(new AttributesAdditions(List.of(attribute)));
        return this;
    }

    public GoalDataBuilder withAttributeAddition(List<AttributesAdditions.AttributesMap> attribute) {
        attributeAdditions.add(new AttributesAdditions(attribute));
        return this;
    }

    public GoalDataBuilder addAttributeAddition(Holder<Attribute> attribute, double value) {
        attributeAdditions.add(new AttributesAdditions(List.of(new AttributesAdditions.AttributesMap(attribute, value))));
        return this;
    }


    public GoalDataBuilder addGoals(GoalCodec... goals) {
        goalOperations.add(new AddOperation(List.of(goals)));
        return this;
    }

    public GoalDataBuilder addAddOperation(List<GoalCodec> goals) {
        goalOperations.add(new AddOperation(goals));
        return this;
    }

    public GoalDataBuilder addGoal(GoalCodec goals) {
        goalOperations.add(new AddOperation(List.of(goals)));
        return this;
    }

    public GoalDataBuilder addRemoveAllOperation() {
        goalOperations.add(new RemoveAllOperation());
        return this;
    }

    public GoalDataBuilder addRemoveSpecificOperation(int priority, Class<? extends Goal> goal) {
        goalOperations.add(new RemoveSpecificOperation(new RemoveSpecificOperation.ReplacementGoal(priority, goal)));
        return this;
    }

    public GoalDataBuilder addRemoveSpecificPriorityOperation(int priorityToRemove) {
        goalOperations.add(new RemoveSpecificPriorityOperation(priorityToRemove));
        return this;
    }

    public GoalDataBuilder addReplaceOperation(int priority, Class<? extends Goal> goal, GoalCodec replacementGoal) {
        goalOperations.add(new ReplaceOperation(new ReplaceOperation.ReplacementGoal(priority, goal), replacementGoal));
        return this;
    }


    ///////////////////
    public GoalDataBuilder addAddTargetOperation(List<TargetGoalCodec> goals) {
        targetGoalOperations.add(new AddTargetOperation(goals));
        return this;
    }

    public GoalDataBuilder addTargetGoal(TargetGoalCodec goals) {
        targetGoalOperations.add(new AddTargetOperation(List.of(goals)));
        return this;
    }

    public GoalDataBuilder addTargetGoals(TargetGoalCodec... goals) {
        targetGoalOperations.add(new AddTargetOperation(List.of(goals)));
        return this;
    }

    public GoalDataBuilder addRemoveAllTargetOperation() {
        targetGoalOperations.add(new RemoveAllTargetOperation());
        return this;
    }

    public GoalDataBuilder addRemoveSpecificTargetOperation(int priority, Class<? extends TargetGoal> goal) {
        targetGoalOperations.add(new RemoveSpecificTargetOperation(new RemoveSpecificTargetOperation.ReplacementGoal(priority, goal)));
        return this;
    }

    public GoalDataBuilder addRemoveSpecificTargetPriorityOperation(int priorityToRemove) {
        targetGoalOperations.add(new RemoveSpecificTargetPriorityOperation(priorityToRemove));
        return this;
    }

    public GoalDataBuilder addReplaceOperation(int priority, Class<? extends TargetGoal> goal, TargetGoalCodec replacementGoal) {
        targetGoalOperations.add(new ReplaceTargetOperation(new ReplaceTargetOperation.ReplacementGoal(priority, goal), replacementGoal));
        return this;
    }

    public GoalData build() {
        return new GoalData(targetEntity, goalOperations, targetGoalOperations, attributeAdditions);
    }

    public GoalDataProvider.GoalDataConsumer buildGoalDataConsumer() {
        return  new GoalDataProvider.GoalDataConsumer(targetEntity, new GoalData(targetEntity, goalOperations, targetGoalOperations, attributeAdditions));
    }
}

package com.scouter.goalsmith.data.operation.target;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import com.scouter.goalsmith.data.TargetGoalCodec;
import com.scouter.goalsmith.data.TargetGoalOperation;
import net.minecraft.world.entity.PathfinderMob;

import java.util.List;

public record AddTargetOperation(List<TargetGoalCodec> goal) implements TargetGoalOperation {
    public static final Codec<AddTargetOperation> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    TargetGoalCodec.DIRECT_CODEC.listOf().fieldOf("target_goal").forGetter(AddTargetOperation::goal)
            ).apply(instance, AddTargetOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        for(TargetGoalCodec goal : goal) {
            goal.addTargetGoal(mob);
        }
    }

    @Override
    public Codec<? extends TargetGoalOperation> codec() {
        return GoalOperationRegistry.ADD_TARGET_GOAL.get();
    }
}

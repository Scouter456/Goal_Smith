package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;

import java.util.List;

public record AddOperation(List<GoalCodec> goal) implements GoalOperation {
    public static final MapCodec<AddOperation> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    GoalCodec.DIRECT_CODEC.listOf().fieldOf("goal").forGetter(AddOperation::goal)
            ).apply(instance, AddOperation::new)
    );

    @Override
    public void performOperation(PathfinderMob mob) {
        for(GoalCodec goal : goal) {
            goal.addGoal(mob);
        }
    }

    @Override
    public MapCodec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.ADD_GOAL.get();
    }
}

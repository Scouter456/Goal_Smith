package com.scouter.goalsmith.data.operation.target;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import com.scouter.goalsmith.data.TargetGoalOperation;
import net.minecraft.world.entity.PathfinderMob;

public class RemoveAllTargetOperation implements TargetGoalOperation {

    public static final MapCodec<RemoveAllTargetOperation> CODEC = MapCodec.unit(RemoveAllTargetOperation::new);


    @Override
    public void performOperation(PathfinderMob mob) {
        mob.targetSelector.removeAllGoals(e->true);
    }

    @Override
    public MapCodec<? extends TargetGoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_ALL_TARGET_GOALS.get();
    }
}

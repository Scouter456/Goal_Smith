package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;

public class RemoveAllOperation implements GoalOperation {

    public static final MapCodec<RemoveAllOperation> CODEC = MapCodec.unit(RemoveAllOperation::new);


    @Override
    public void performOperation(PathfinderMob mob) {
        mob.goalSelector.removeAllGoals(e->true);
    }

    @Override
    public MapCodec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_ALL_GOALS.get();
    }
}

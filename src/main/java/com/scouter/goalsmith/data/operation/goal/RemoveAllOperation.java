package com.scouter.goalsmith.data.operation.goal;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.GoalOperation;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import net.minecraft.world.entity.PathfinderMob;

public class RemoveAllOperation implements GoalOperation {

    public static final Codec<RemoveAllOperation> CODEC = Codec.unit(RemoveAllOperation::new);


    @Override
    public void performOperation(PathfinderMob mob) {
        mob.goalSelector.removeAllGoals(e->true);
    }

    @Override
    public Codec<? extends GoalOperation> codec() {
        return GoalOperationRegistry.REMOVE_ALL_GOALS.get();
    }
}

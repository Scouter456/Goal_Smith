package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.entity.PathfinderMob;

import java.util.function.Function;

public interface GoalOperation {
    Codec<GoalOperation> DIRECT_CODEC = PMRegistries.GOAL_OPERATION_SERIALIZER.byNameCodec().dispatch(GoalOperation::codec, Function.identity());

    Codec<Holder<GoalOperation>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.GOAL_OPERATION, DIRECT_CODEC);

    void performOperation(PathfinderMob mob);
    MapCodec<? extends GoalOperation> codec();
}

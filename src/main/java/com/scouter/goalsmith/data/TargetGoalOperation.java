package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.entity.PathfinderMob;

import java.util.function.Function;

public interface TargetGoalOperation {
    Codec<TargetGoalOperation> DIRECT_CODEC = PMRegistries.TARGET_GOAL_OPERATION_SERIALIZER.byNameCodec().dispatch(TargetGoalOperation::codec, Function.identity());

    Codec<Holder<TargetGoalOperation>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.TARGET_GOAL_OPERATION, DIRECT_CODEC);

    void performOperation(PathfinderMob mob);
    MapCodec<? extends TargetGoalOperation> codec();
}

package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.PathfinderMob;

import java.util.function.Function;

public interface TargetGoalOperation {
    Codec<TargetGoalOperation> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> PMRegistries.TARGET_GOAL_OPERATION_SERIALIZER_SUPPLIER.get().getCodec()).dispatch(TargetGoalOperation::codec, Function.identity());

    Codec<Holder<TargetGoalOperation>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.TARGET_GOAL_OPERATION, DIRECT_CODEC);

    void performOperation(PathfinderMob mob);
    Codec<? extends TargetGoalOperation> codec();
}

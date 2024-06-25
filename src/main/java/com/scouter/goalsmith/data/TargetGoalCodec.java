package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Function;

public interface TargetGoalCodec {
    Codec<TargetGoalCodec> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> PMRegistries.TARGET_GOAL_TYPE_SERIALIZER_SUPPLIER.get().getCodec()).dispatch(TargetGoalCodec::codec, Function.identity());

    Codec<Holder<TargetGoalCodec>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.TARGET_GOAL_TYPE, DIRECT_CODEC);

    Goal addTargetGoal(PathfinderMob mob);
    Codec<? extends TargetGoalCodec> codec();
}

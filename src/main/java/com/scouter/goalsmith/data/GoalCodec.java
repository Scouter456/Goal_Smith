package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Function;

public interface GoalCodec {
    Codec<GoalCodec> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> PMRegistries.GOAL_TYPE_SERIALIZER_SUPPLIER.get().getCodec()).dispatch(GoalCodec::codec, Function.identity());

    Codec<Holder<GoalCodec>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.GOAL_TYPE, DIRECT_CODEC);

    Goal addGoal(PathfinderMob mob);
    Codec<? extends GoalCodec> codec();
}

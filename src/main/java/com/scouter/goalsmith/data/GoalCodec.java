package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Function;

public interface GoalCodec {
    Codec<GoalCodec> DIRECT_CODEC = PMRegistries.GOAL_TYPE_SERIALIZER.byNameCodec().dispatch(GoalCodec::codec, Function.identity());

    Codec<Holder<GoalCodec>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.GOAL_TYPE, DIRECT_CODEC);

    Goal addGoal(PathfinderMob mob);
    MapCodec<? extends GoalCodec> codec();
}

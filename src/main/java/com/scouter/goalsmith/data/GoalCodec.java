package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Function;

public interface GoalCodec {
    Codec<GoalCodec> DIRECT_CODEC = GSRegistries.GOAL_TYPE_SERIALIZER.byNameCodec().dispatch(GoalCodec::codec, Function.identity());

    Codec<Holder<GoalCodec>> REFERENCE_CODEC = RegistryFileCodec.create(GSRegistries.Keys.GOAL_TYPE, DIRECT_CODEC);

    Goal addGoal(PathfinderMob mob);
    Codec<? extends GoalCodec> codec();
}

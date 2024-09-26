package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Function;

public interface TargetGoalCodec {
    Codec<TargetGoalCodec> DIRECT_CODEC = GSRegistries.TARGET_GOAL_TYPE_SERIALIZER.byNameCodec().dispatch(TargetGoalCodec::codec, Function.identity());

    Codec<Holder<TargetGoalCodec>> REFERENCE_CODEC = RegistryFileCodec.create(GSRegistries.Keys.TARGET_GOAL_TYPE, DIRECT_CODEC);

    Goal addTargetGoal(PathfinderMob mob);
    Codec<? extends TargetGoalCodec> codec();
}

package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.server.level.ServerLevel;

import java.util.function.Function;

public interface EntityTargetType {
    Codec<EntityTargetType> DIRECT_CODEC = GSRegistries.ENTITY_TARGET_TYPE_TYPE_SERIALIZER.byNameCodec().dispatch(EntityTargetType::codec, Function.identity());

    Codec<Holder<EntityTargetType>> REFERENCE_CODEC = RegistryFileCodec.create(GSRegistries.Keys.ENTITY_TARGET_TYPE, DIRECT_CODEC);

    void apply(ServerLevel level, GoalData data);
    MapCodec<? extends EntityTargetType> codec();
}

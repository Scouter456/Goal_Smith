package com.scouter.goalsmith.data;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;

public interface EntityTargetType {
    Codec<EntityTargetType> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> GSRegistries.ENTITY_TARGET_TYPE_TYPE_SERIALIZER_SUPPLIER.get().getCodec()).dispatch(EntityTargetType::codec, Function.identity());

    Codec<Holder<EntityTargetType>> REFERENCE_CODEC = RegistryFileCodec.create(GSRegistries.Keys.ENTITY_TARGET_TYPE, DIRECT_CODEC);

    void apply(ServerLevel level, GoalData data);
    Codec<? extends EntityTargetType> codec();
}

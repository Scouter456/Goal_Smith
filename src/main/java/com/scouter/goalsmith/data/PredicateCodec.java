package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;

import java.util.function.Function;
import java.util.function.Predicate;

public interface PredicateCodec<T> {
    Codec<PredicateCodec<?>> DIRECT_CODEC = PMRegistries.PREDICATE_TYPE_SERIALIZER.byNameCodec().dispatch(PredicateCodec::codec, Function.identity());

    Codec<Holder<PredicateCodec<?>>> REFERENCE_CODEC = RegistryFileCodec.create(PMRegistries.Keys.PREDICATE_TYPE, DIRECT_CODEC);
    Predicate<T> getPredicate();
    MapCodec<? extends PredicateCodec<T>> codec();
}

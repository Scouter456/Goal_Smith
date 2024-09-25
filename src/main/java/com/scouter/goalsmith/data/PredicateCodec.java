package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.util.ExtraCodecs;

import java.util.function.Function;
import java.util.function.Predicate;

public interface PredicateCodec<T> {
    Codec<PredicateCodec<?>> DIRECT_CODEC = ExtraCodecs.lazyInitializedCodec(() -> GSRegistries.PREDICATE_TYPE_SERIALIZER_SUPPLIER.get().getCodec()).dispatch(PredicateCodec::codec, Function.identity());

    Codec<Holder<PredicateCodec<?>>> REFERENCE_CODEC = RegistryFileCodec.create(GSRegistries.Keys.PREDICATE_TYPE, DIRECT_CODEC);
    Predicate<T> getPredicate();
    Codec<? extends PredicateCodec<T>> codec();
}

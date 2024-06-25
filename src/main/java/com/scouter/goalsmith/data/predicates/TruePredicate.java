package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;

import java.util.function.Predicate;

public class TruePredicate<T> implements PredicateCodec<T> {
    public static final Codec<TruePredicate<?>> CODEC = Codec.unit(TruePredicate::new);

    @Override
    public Predicate<T> getPredicate() {
        return e -> true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<TruePredicate<T>> codec() {
        return (Codec<TruePredicate<T>>) PredicateRegistry.TRUE_PREDICATE.get();
    }
}

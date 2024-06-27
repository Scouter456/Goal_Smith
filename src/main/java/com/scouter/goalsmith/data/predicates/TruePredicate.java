package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;

import java.util.function.Predicate;

public class TruePredicate<T> implements PredicateCodec<T> {
    public static final MapCodec<TruePredicate<?>> CODEC = MapCodec.unit(TruePredicate::new);

    @Override
    public Predicate<T> getPredicate() {
        return e -> true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<TruePredicate<T>> codec() {
        return (MapCodec<TruePredicate<T>>) PredicateRegistry.TRUE_PREDICATE.get();
    }
}

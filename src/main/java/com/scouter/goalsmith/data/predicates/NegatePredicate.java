package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;

import java.util.Objects;
import java.util.function.Predicate;

public class NegatePredicate<T> implements PredicateCodec<T> {

    private final PredicateCodec<? extends T> predicate;

    public static final Codec<NegatePredicate<?>> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    PredicateCodec.DIRECT_CODEC.fieldOf("predicate").forGetter(NegatePredicate::getPredicateC)
            ).apply(instance, NegatePredicate::new)
    );

    public NegatePredicate(PredicateCodec<? extends T> predicate) {
        this.predicate = Objects.requireNonNull(predicate);
    }

    public PredicateCodec<? extends T> getPredicateC() {
        return predicate;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Predicate<T> getPredicate() {
        return ((Predicate<T>) predicate.getPredicate()).negate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<NegatePredicate<T>> codec() {
        return (Codec<NegatePredicate<T>>) PredicateRegistry.NEGATE_PREDICATE.get();
    }
}

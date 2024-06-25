package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;

import java.util.function.Predicate;

public class OrPredicate<T> implements PredicateCodec<T> {

    private final PredicateCodec<? extends T> predicate1;
    private final PredicateCodec<? extends T> predicate2;

    public static final Codec<OrPredicate<?>> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    PredicateCodec.DIRECT_CODEC.fieldOf("predicate_1").forGetter(OrPredicate::getPredicate1),
                    PredicateCodec.DIRECT_CODEC.fieldOf("predicate_2").forGetter(OrPredicate::getPredicate2)
            ).apply(instance, OrPredicate::new)
    );

    public OrPredicate(PredicateCodec<? extends T> predicate1, PredicateCodec<? extends T> predicate2) {
        this.predicate1 = predicate1;
        this.predicate2 = predicate2;
    }

    public PredicateCodec<? extends T> getPredicate1() {
        return predicate1;
    }

    public PredicateCodec<? extends T> getPredicate2() {
        return predicate2;
    }

    @SuppressWarnings("unchecked")
    public Predicate<T> getPredicate() {
        return ((Predicate<T>) predicate1.getPredicate()).or((Predicate<? super T>) predicate2.getPredicate());
    }


    @SuppressWarnings("unchecked")
    @Override
    public Codec<OrPredicate<T>> codec() {
        return (Codec<OrPredicate<T>>) PredicateRegistry.OR_PREDICATE.get();
    }
}


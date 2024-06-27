package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;

import java.util.Objects;
import java.util.function.Predicate;

public class AndPredicate<T> implements PredicateCodec<T> {

    private final PredicateCodec<? extends T> predicate1;
    private final PredicateCodec<? extends T> predicate2;

    public static final MapCodec<AndPredicate<?>> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    PredicateCodec.DIRECT_CODEC.fieldOf("predicate_1").forGetter(AndPredicate::getPredicate1),
                    PredicateCodec.DIRECT_CODEC.fieldOf("predicate_2").forGetter(AndPredicate::getPredicate2)
            ).apply(instance, AndPredicate::new)
    );

    public AndPredicate(PredicateCodec<? extends T> predicate1, PredicateCodec<? extends T> predicate2) {
        this.predicate1 = Objects.requireNonNull(predicate1);
        this.predicate2 = Objects.requireNonNull(predicate2);
    }

    public PredicateCodec<? extends T> getPredicate1() {
        return predicate1;
    }

    public PredicateCodec<? extends T> getPredicate2() {
        return predicate2;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate<T> getPredicate() {
        return ((Predicate<T>) predicate1.getPredicate()).and((Predicate<? super T>) predicate2.getPredicate());
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<AndPredicate<T>> codec() {
        return (MapCodec<AndPredicate<T>>) PredicateRegistry.AND_PREDICATE.get();
    }
}

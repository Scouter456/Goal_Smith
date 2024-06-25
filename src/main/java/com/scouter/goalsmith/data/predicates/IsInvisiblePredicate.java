package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class IsInvisiblePredicate implements PredicateCodec<Entity> {

    public static final Codec<IsInvisiblePredicate> CODEC = Codec.unit(IsInvisiblePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> entity.isInvisible();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_INVISIBLE.get();
    }
}
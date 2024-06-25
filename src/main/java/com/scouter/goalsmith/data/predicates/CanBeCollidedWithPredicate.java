package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class CanBeCollidedWithPredicate implements PredicateCodec<Entity> {
    public static final Codec<CanBeCollidedWithPredicate> CODEC = Codec.unit(CanBeCollidedWithPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return e -> !e.isSpectator() && e.canBeCollidedWith();
    }

    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.CAN_BE_COLLIDED_WITH.get();
    }
}

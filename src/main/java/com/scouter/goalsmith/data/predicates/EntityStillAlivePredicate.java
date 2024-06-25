package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityStillAlivePredicate implements PredicateCodec<Entity> {

    public static final Codec<EntityStillAlivePredicate> CODEC = Codec.unit(EntityStillAlivePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return Entity::isAlive;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.ENTITY_IS_ALIVE.get();
    }
}

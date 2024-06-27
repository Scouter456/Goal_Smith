package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityStillAlivePredicate implements PredicateCodec<Entity> {

    public static final MapCodec<EntityStillAlivePredicate> CODEC = MapCodec.unit(EntityStillAlivePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return Entity::isAlive;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.ENTITY_IS_ALIVE.get();
    }
}

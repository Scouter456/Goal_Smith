package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityNotBeingRiddenPredicate implements PredicateCodec<Entity> {
    public static final Codec<EntityNotBeingRiddenPredicate> CODEC = Codec.unit(EntityNotBeingRiddenPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return e -> e.isAlive() && !e.isVehicle() && !e.isPassenger();
    }

    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.ENTITY_NOT_BEING_RIDDEN.get();
    }
}

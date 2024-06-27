package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityNotBeingRiddenPredicate implements PredicateCodec<Entity> {
    public static final MapCodec<EntityNotBeingRiddenPredicate> CODEC = MapCodec.unit(EntityNotBeingRiddenPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return e -> e.isAlive() && !e.isVehicle() && !e.isPassenger();
    }

    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.ENTITY_NOT_BEING_RIDDEN.get();
    }
}

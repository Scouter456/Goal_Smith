package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityIsVehiclePredicate implements PredicateCodec<Entity> {
    public static final Codec<EntityIsVehiclePredicate> CODEC = Codec.unit(EntityIsVehiclePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return Entity::isVehicle;
    }

    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.ENTITY_IS_VEHICLE.get();
    }
}

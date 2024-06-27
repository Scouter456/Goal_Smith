package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class EntityIsVehiclePredicate implements PredicateCodec<Entity> {
    public static final MapCodec<EntityIsVehiclePredicate> CODEC = MapCodec.unit(EntityIsVehiclePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return Entity::isVehicle;
    }

    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.ENTITY_IS_VEHICLE.get();
    }
}

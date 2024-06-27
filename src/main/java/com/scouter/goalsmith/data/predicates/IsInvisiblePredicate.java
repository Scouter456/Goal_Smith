package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class IsInvisiblePredicate implements PredicateCodec<Entity> {

    public static final MapCodec<IsInvisiblePredicate> CODEC = MapCodec.unit(IsInvisiblePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return Entity::isInvisible;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_INVISIBLE.get();
    }
}

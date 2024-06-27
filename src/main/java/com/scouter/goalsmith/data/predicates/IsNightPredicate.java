package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;

import java.util.function.Predicate;

public class IsNightPredicate implements PredicateCodec<Entity> {

    public static final MapCodec<IsNightPredicate> CODEC = MapCodec.unit(IsNightPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> entity.level().isNight();
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_NIGHT.get();
    }
}

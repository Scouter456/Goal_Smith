package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class IsPlayerPredicate implements PredicateCodec<Entity> {

    public static final MapCodec<IsPlayerPredicate> CODEC = MapCodec.unit(IsPlayerPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> entity instanceof Player;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_PLAYER.get();
    }
}
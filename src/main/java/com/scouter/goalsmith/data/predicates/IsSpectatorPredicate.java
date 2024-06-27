package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class IsSpectatorPredicate implements PredicateCodec<Entity> {

    public static final MapCodec<IsSpectatorPredicate> CODEC = MapCodec.unit(IsSpectatorPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> {
            if (entity instanceof Player player) {
                return player.isSpectator();
            }
            return false; // Non-players are considered not spectator
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_SPECTATOR.get();
    }
}
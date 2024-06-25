package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class IsPlayerPredicate implements PredicateCodec<Entity> {

    public static final Codec<IsPlayerPredicate> CODEC = Codec.unit(IsPlayerPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> entity instanceof Player;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_PLAYER.get();
    }
}
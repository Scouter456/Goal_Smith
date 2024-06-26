package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class IsCreativePredicate implements PredicateCodec<Entity> {

    public static final Codec<IsCreativePredicate> CODEC = Codec.unit(IsCreativePredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> {
            if (entity instanceof Player player) {
                return player.isCreative();
            }
            return false; // Non-players are considered not creative
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.IS_CREATIVE.get();
    }
}

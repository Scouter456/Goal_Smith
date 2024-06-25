package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class NoCreativeOrSpectatorPredicate implements PredicateCodec<Entity> {
    public static final Codec<NoCreativeOrSpectatorPredicate> CODEC = Codec.unit(NoCreativeOrSpectatorPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return e -> !(e instanceof Player) || !e.isSpectator() && !((Player)e).isCreative();

    }

    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.NO_CREATIVE_OR_SPECTATOR.get();
    }
}

package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class NoCreativeOrSpectatorPredicate implements PredicateCodec<Entity> {
    public static final MapCodec<NoCreativeOrSpectatorPredicate> CODEC = MapCodec.unit(NoCreativeOrSpectatorPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return e -> !(e instanceof Player) || !e.isSpectator() && !((Player)e).isCreative();

    }

    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return PredicateRegistry.NO_CREATIVE_OR_SPECTATOR.get();
    }
}

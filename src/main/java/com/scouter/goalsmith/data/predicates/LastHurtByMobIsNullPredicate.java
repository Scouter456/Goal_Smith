package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;

public class LastHurtByMobIsNullPredicate implements PredicateCodec<Entity> {

    public static final Codec<LastHurtByMobIsNullPredicate> CODEC = Codec.unit(LastHurtByMobIsNullPredicate::new);

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> {
            if(entity instanceof LivingEntity entity1) {
              return  entity1.getLastHurtByMob() == null;
            }
           return true;
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Codec<? extends PredicateCodec<Entity>> codec() {
        return (Codec<? extends PredicateCodec<Entity>>) PredicateRegistry.LAST_HURT_BY_MOB_IS_NULL.get();
    }
}
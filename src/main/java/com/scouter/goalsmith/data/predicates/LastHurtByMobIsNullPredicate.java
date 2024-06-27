package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;

public class LastHurtByMobIsNullPredicate implements PredicateCodec<Entity> {

    public static final MapCodec<LastHurtByMobIsNullPredicate> CODEC = MapCodec.unit(LastHurtByMobIsNullPredicate::new);

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
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.LAST_HURT_BY_MOB_IS_NULL.get();
    }
}
package com.scouter.goalsmith.data.predicates;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Predicate;

public class EntityTypePredicate implements PredicateCodec<Entity> {

    private final EntityType<?> entityType;

    public static final MapCodec<EntityTypePredicate> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(e -> e.entityType)
            ).apply(instance, EntityTypePredicate::new)
    );

    public EntityTypePredicate(EntityType<?> entityType) {
        this.entityType = entityType;
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    @Override
    public Predicate<Entity> getPredicate() {
        return entity -> entity.getType() == entityType;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MapCodec<? extends PredicateCodec<Entity>> codec() {
        return (MapCodec<? extends PredicateCodec<Entity>>) PredicateRegistry.ENTITY_TYPE_PREDICATE.get();
    }
}
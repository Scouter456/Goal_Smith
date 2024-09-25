package com.scouter.goalsmith.data.targets;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.data.EntityTargetType;
import com.scouter.goalsmith.data.EntityTargetTypeRegistry;
import com.scouter.goalsmith.data.GoalData;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class TagTargetType implements EntityTargetType {

    public static final MapCodec<TagTargetType> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    TagKey.codec(Registries.ENTITY_TYPE).fieldOf("target_tag").forGetter(TagTargetType::getType)
            ).apply(instance, TagTargetType::new)
    );

    private TagKey<EntityType<?>> type;

    public TagTargetType(TagKey<EntityType<?>> location) {
        this.type = location;
    }

    public TagKey<EntityType<?>> getType() {
        return type;
    }

    @Override
    public void apply(ServerLevel level, GoalData goalData) {
        level.registryAccess().registry(Registries.ENTITY_TYPE).ifPresent(
                entityTypes -> {
                    Iterable<Holder<EntityType<?>>> holders = entityTypes.getTagOrEmpty(type);
                    for(Holder<EntityType<?>> holder : holders) {
                        holder.unwrapKey().ifPresent(
                             entity -> {
                                 List<GoalData> data = EntityGoalJsonManager.getEntityData().computeIfAbsent(entity.location(), (l) -> new ArrayList<>());
                                 data.add(goalData);
                             });
                             }
                    }
        );
    }

    @Override
    public MapCodec<? extends EntityTargetType> codec() {
        return EntityTargetTypeRegistry.TAG_TARGET.get();
    }
}

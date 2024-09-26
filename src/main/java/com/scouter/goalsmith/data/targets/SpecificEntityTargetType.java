package com.scouter.goalsmith.data.targets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.data.EntityTargetType;
import com.scouter.goalsmith.data.EntityTargetTypeRegistry;
import com.scouter.goalsmith.data.GoalData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;

import java.util.ArrayList;
import java.util.List;

public class SpecificEntityTargetType implements EntityTargetType {

    public static final Codec<SpecificEntityTargetType> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("target_entity").forGetter(SpecificEntityTargetType::getType)
            ).apply(instance, SpecificEntityTargetType::new)
    );

    private ResourceLocation type;

    public SpecificEntityTargetType(ResourceLocation location) {
        this.type = location;
    }

    public ResourceLocation getType() {
        return type;
    }

    @Override
    public void apply(ServerLevel level, GoalData goalData) {
        List<GoalData> data = EntityGoalJsonManager.getEntityData().computeIfAbsent(type, (l) -> new ArrayList<>());
        data.add(goalData);
    }

    @Override
    public Codec<? extends EntityTargetType> codec() {
        return EntityTargetTypeRegistry.SPECIFIC_TARGET;
    }
}

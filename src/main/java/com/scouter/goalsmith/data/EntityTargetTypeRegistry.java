package com.scouter.goalsmith.data;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.targets.AllEntityTargetType;
import com.scouter.goalsmith.data.targets.SpecificEntityTargetType;
import com.scouter.goalsmith.data.targets.TagTargetType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityTargetTypeRegistry {

    public static final DeferredRegister<MapCodec<? extends EntityTargetType>> ENTITY_TARGET_TYPE = DeferredRegister.create(GSRegistries.Keys.ENTITY_TARGET_TYPE_SERIALIZERS, GoalSmith.MODID);

    public static final DeferredHolder<MapCodec<? extends EntityTargetType>, ?> SPECIFIC_TARGET = ENTITY_TARGET_TYPE.register("specific", () -> SpecificEntityTargetType.CODEC);
    public static final DeferredHolder<MapCodec<? extends EntityTargetType>, ?> ALL_TARGET = ENTITY_TARGET_TYPE.register("all", () -> AllEntityTargetType.CODEC);
    public static final DeferredHolder<MapCodec<? extends EntityTargetType>, ?> TAG_TARGET = ENTITY_TARGET_TYPE.register("tag", () -> TagTargetType.CODEC);

}

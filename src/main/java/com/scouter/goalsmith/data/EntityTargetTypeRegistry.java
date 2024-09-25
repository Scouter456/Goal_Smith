package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.predicates.OrPredicate;
import com.scouter.goalsmith.data.targets.AllEntityTargetType;
import com.scouter.goalsmith.data.targets.SpecificEntityTargetType;
import com.scouter.goalsmith.data.targets.TagTargetType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EntityTargetTypeRegistry {

    public static final DeferredRegister<Codec<? extends EntityTargetType>> ENTITY_TARGET_TYPE = DeferredRegister.create(GSRegistries.Keys.ENTITY_TARGET_TYPE_SERIALIZERS, GoalSmith.MODID);

    public static final RegistryObject<Codec<? extends EntityTargetType>> SPECIFIC_TARGET = ENTITY_TARGET_TYPE.register("specific", () -> SpecificEntityTargetType.CODEC);
    public static final RegistryObject<Codec<? extends EntityTargetType>> ALL_TARGET = ENTITY_TARGET_TYPE.register("all", () -> AllEntityTargetType.CODEC);
    public static final RegistryObject<Codec<? extends EntityTargetType>> TAG_TARGET = ENTITY_TARGET_TYPE.register("tag", () -> TagTargetType.CODEC);

}

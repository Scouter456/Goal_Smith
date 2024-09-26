package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.targets.AllEntityTargetType;
import com.scouter.goalsmith.data.targets.SpecificEntityTargetType;
import com.scouter.goalsmith.data.targets.TagTargetType;
import net.minecraft.core.Registry;

import static com.scouter.goalsmith.Goalsmith.prefix;

public class EntityTargetTypeRegistry {


    public static final Codec<? extends EntityTargetType> SPECIFIC_TARGET = registerEntityTargetType("specific",SpecificEntityTargetType.CODEC);
    public static final Codec<? extends EntityTargetType> ALL_TARGET =registerEntityTargetType("all", AllEntityTargetType.CODEC);
    public static final Codec<? extends EntityTargetType> TAG_TARGET = registerEntityTargetType("tag", TagTargetType.CODEC);

    private static Codec<? extends EntityTargetType> registerEntityTargetType(String name, Codec<? extends EntityTargetType> type) {
        return Registry.register(GSRegistries.ENTITY_TARGET_TYPE_TYPE_SERIALIZER, prefix(name), type);
    }

    public static void register()
    {
    }
}

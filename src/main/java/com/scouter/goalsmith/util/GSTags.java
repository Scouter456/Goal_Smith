package com.scouter.goalsmith.util;

import com.scouter.goalsmith.GoalSmith;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class GSTags {

    public static final TagKey<EntityType<?>> MONSTER = registerEntityTag("monster");
    public static final TagKey<EntityType<?>> AMBIENT = registerEntityTag("ambient");
    public static final TagKey<EntityType<?>> CREATURE = registerEntityTag("creature");
    public static final TagKey<EntityType<?>> MISC = registerEntityTag("misc");
    public static final TagKey<EntityType<?>> WATER_CREATURE = registerEntityTag("water_creature");
    public static final TagKey<EntityType<?>> LIVING_ENTITY = registerEntityTag("living_entity");
    public static final TagKey<EntityType<?>> ANIMAL = registerEntityTag("animal");
    public static final TagKey<EntityType<?>> FISH = registerEntityTag("fish");
    public static final TagKey<EntityType<?>> ILLAGER = registerEntityTag("illager");
    public static final TagKey<EntityType<?>> PLAYER = registerEntityTag("player");


    private static TagKey<EntityType<?>> registerEntityTag(String name) {

        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(GoalSmith.MODID, name));
    }
}


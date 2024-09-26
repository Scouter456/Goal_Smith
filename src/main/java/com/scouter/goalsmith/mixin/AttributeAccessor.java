package com.scouter.goalsmith.mixin;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

@Mixin(AttributeMap.class)
public interface AttributeAccessor {
    @Accessor("attributes")
    Map<Attribute, AttributeInstance> goalsmith$getAttributes();

    @Accessor("dirtyAttributes")
    Set<AttributeInstance> goalsmith$getDirtyAttributes();



}

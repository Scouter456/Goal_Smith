package com.scouter.goalsmith.mixin;

import net.minecraft.core.Holder;
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
    Map<Holder<Attribute>, AttributeInstance> goalsmith$getAttributes();

    @Accessor("attributesToUpdate")
    Set<AttributeInstance> goalsmith$getAttributesToUpdate();

    @Accessor("attributesToSync")
    Set<AttributeInstance> goalsmith$getAttributesToSync();

}

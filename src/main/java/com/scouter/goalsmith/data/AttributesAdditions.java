package com.scouter.goalsmith.data;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.mixin.AttributeAccessor;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;
import java.util.UUID;

public class AttributesAdditions implements AttributeAdditions{

    private static final Codec<AttributesMap> ATTRIBUTES_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ATTRIBUTE.byNameCodec().fieldOf("attribute").forGetter(AttributesMap::getAttribute),
            Codec.DOUBLE.fieldOf("value").forGetter(AttributesMap::getValue),
            UUIDUtil.CODEC.fieldOf("uuid").forGetter(AttributesMap::getUuid)
    ).apply(instance, AttributesMap::new));


    public static final Codec<AttributesAdditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ATTRIBUTES_CODEC.listOf().fieldOf("attribute_definitions").forGetter(AttributesAdditions::getAttributeMap)
    ).apply(instance, AttributesAdditions::new));



    private final List<AttributesMap> maps;

    public AttributesAdditions(List<AttributesMap> attributes) {
        maps = attributes;
    }

    public List<AttributesMap> getAttributeMap() {
        return maps;
    }

    @Override
    public void performAdditions(PathfinderMob mob) {
        for(AttributesMap attributeMap : getAttributeMap()) {
            if(mob.getAttributes().hasAttribute(attributeMap.attribute)) {
                mob.getAttribute(attributeMap.attribute).setBaseValue(attributeMap.value);
            } else {
                AttributeInstance instance = new AttributeInstance(attributeMap.attribute , e -> onAttributeModified(e, mob));
                instance.setBaseValue(attributeMap.value);
                ((AttributeAccessor) mob.getAttributes()).goalsmith$getAttributes().put(attributeMap.attribute, instance);
            }
        }

    }

    public void onAttributeModified(AttributeInstance attributeInstance, PathfinderMob mob) {
        if (attributeInstance.getAttribute().isClientSyncable()) {
            ((AttributeAccessor) mob.getAttributes()).goalsmith$getDirtyAttributes().add(attributeInstance);
        }

    }

    public static class AttributesMap {



        private final Attribute attribute;
        private final double value;
        private final UUID uuid;
        private final Multimap<Attribute, AttributeModifier> defaultModifiers;


        public AttributesMap(Attribute attribute, double value, UUID uuid) {
            this.attribute = attribute;
            this.value = value;
            this.uuid = uuid;
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(attribute, new AttributeModifier(uuid, "attribute modifier", (double)value, AttributeModifier.Operation.ADDITION));
            this.defaultModifiers = builder.build();
        }

        // Getter method for attribute
        public Attribute getAttribute() {
            return attribute;
        }

        // Getter method for value
        public double getValue() {
            return value;
        }

        // Getter method for uuid
        public UUID getUuid() {
            return uuid;
        }

        public Multimap<Attribute, AttributeModifier> getDefaultModifiers() {
            return defaultModifiers;
        }
    }

}

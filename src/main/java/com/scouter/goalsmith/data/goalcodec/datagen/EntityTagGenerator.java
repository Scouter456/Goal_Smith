package com.scouter.goalsmith.data.goalcodec.datagen;

import com.scouter.goalsmith.GoalSmith;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class EntityTagGenerator extends EntityTypeTagsProvider {


    public EntityTagGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, GoalSmith.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        TagKey< EntityType<?> > ambient = TagKey.create(Registries.ENTITY_TYPE, prefix("ambient"));
        TagKey< EntityType<?> > creature = TagKey.create(Registries.ENTITY_TYPE, prefix("creature"));
        TagKey< EntityType<?> > monster = TagKey.create(Registries.ENTITY_TYPE, prefix("monster"));
        TagKey< EntityType<?> > misc = TagKey.create(Registries.ENTITY_TYPE, prefix("misc"));
        TagKey< EntityType<?> > waterCreature = TagKey.create(Registries.ENTITY_TYPE, prefix("water_creature"));


        BuiltInRegistries.ENTITY_TYPE.iterator().forEachRemaining(e -> {
            String name = e.getDefaultLootTable().location().getPath().split("/")[1];
            TagKey< EntityType<?> > tag = TagKey.create(Registries.ENTITY_TYPE, prefix(name));
            EntityType<?> entityType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.tryParse(name));
            this.tag(tag).add(entityType);

            if(entityType.getCategory() == MobCategory.AMBIENT) {
                tag(ambient).add(entityType);
            }
            if(entityType.getCategory() == MobCategory.CREATURE) {
                tag(creature).add(entityType);
            }
            if(entityType.getCategory() == MobCategory.MONSTER) {
                tag(monster).add(entityType);
            }
            if(entityType.getCategory() == MobCategory.MISC) {
                tag(misc).add(entityType);
            }
            if(entityType.getCategory() == MobCategory.WATER_CREATURE) {
                tag(waterCreature).add(entityType);
            }

        });

        TagKey< EntityType<?> > entity = TagKey.create(Registries.ENTITY_TYPE, prefix("living_entity"));
        TagKey< EntityType<?> > animal = TagKey.create(Registries.ENTITY_TYPE, prefix("animal"));
        TagKey< EntityType<?> > fish = TagKey.create(Registries.ENTITY_TYPE, prefix("fish"));
        TagKey< EntityType<?> > illager = TagKey.create(Registries.ENTITY_TYPE, prefix("illager"));

        // Add all specified entities to the living entity tag
        tag(entity).add(EntityType.ALLAY);
        tag(entity).add(EntityType.AXOLOTL);
        tag(entity).add(EntityType.BEE);
        tag(entity).add(EntityType.BLAZE);
        tag(entity).add(EntityType.CAT);
        tag(entity).add(EntityType.CAVE_SPIDER);
        tag(entity).add(EntityType.CHICKEN);
        tag(entity).add(EntityType.COD);
        tag(entity).add(EntityType.COW);
        tag(entity).add(EntityType.CREEPER);
        tag(entity).add(EntityType.DOLPHIN);
        tag(entity).add(EntityType.DONKEY);
        tag(entity).add(EntityType.DROWNED);
        tag(entity).add(EntityType.ELDER_GUARDIAN);
        tag(entity).add(EntityType.ENDER_DRAGON);
        tag(entity).add(EntityType.ENDERMAN);
        tag(entity).add(EntityType.EVOKER);
        tag(entity).add(EntityType.FOX);
        tag(entity).add(EntityType.GHAST);
        tag(entity).add(EntityType.GLOW_SQUID);
        tag(entity).add(EntityType.GOAT);
        tag(entity).add(EntityType.GUARDIAN);
        tag(entity).add(EntityType.HOGLIN);
        tag(entity).add(EntityType.HORSE);
        tag(entity).add(EntityType.HUSK);
        tag(entity).add(EntityType.ILLUSIONER);
        tag(entity).add(EntityType.IRON_GOLEM);
        tag(entity).add(EntityType.LLAMA);
        tag(entity).add(EntityType.MAGMA_CUBE);
        tag(entity).add(EntityType.MOOSHROOM);
        tag(entity).add(EntityType.MULE);
        tag(entity).add(EntityType.OCELOT);
        tag(entity).add(EntityType.PANDA);
        tag(entity).add(EntityType.PARROT);
        tag(entity).add(EntityType.PHANTOM);
        tag(entity).add(EntityType.PIG);
        tag(entity).add(EntityType.PIGLIN);
        tag(entity).add(EntityType.PIGLIN_BRUTE);
        tag(entity).add(EntityType.POLAR_BEAR);
        tag(entity).add(EntityType.PUFFERFISH);
        tag(entity).add(EntityType.RABBIT);
        tag(entity).add(EntityType.RAVAGER);
        tag(entity).add(EntityType.SALMON);
        tag(entity).add(EntityType.SHEEP);
        tag(entity).add(EntityType.SHULKER);
        tag(entity).add(EntityType.SILVERFISH);
        tag(entity).add(EntityType.SKELETON);
        tag(entity).add(EntityType.SKELETON_HORSE);
        tag(entity).add(EntityType.SLIME);
        tag(entity).add(EntityType.SPIDER);
        tag(entity).add(EntityType.SQUID);
        tag(entity).add(EntityType.STRAY);
        tag(entity).add(EntityType.STRIDER);
        tag(entity).add(EntityType.TROPICAL_FISH);
        tag(entity).add(EntityType.TURTLE);
        tag(entity).add(EntityType.VEX);
        tag(entity).add(EntityType.VILLAGER);
        tag(entity).add(EntityType.VINDICATOR);
        tag(entity).add(EntityType.WANDERING_TRADER);
        tag(entity).add(EntityType.WITCH);
        tag(entity).add(EntityType.WITHER);
        tag(entity).add(EntityType.WITHER_SKELETON);
        tag(entity).add(EntityType.WOLF);
        tag(entity).add(EntityType.ZOGLIN);
        tag(entity).add(EntityType.ZOMBIE);
        tag(entity).add(EntityType.ZOMBIE_HORSE);
        tag(entity).add(EntityType.ZOMBIE_VILLAGER);

        //tag(monster).add(EntityType.BLAZE);
        //tag(monster).add(EntityType.CAVE_SPIDER);
        //tag(monster).add(EntityType.CREEPER);
        //tag(monster).add(EntityType.DROWNED);
        //tag(monster).add(EntityType.ELDER_GUARDIAN);
        //tag(monster).add(EntityType.ENDERMITE);
        //tag(monster).add(EntityType.EVOKER);
        //tag(monster).add(EntityType.GHAST);
        //tag(monster).add(EntityType.GUARDIAN);
        //tag(monster).add(EntityType.HOGLIN);
        //tag(monster).add(EntityType.HUSK);
        //tag(monster).add(EntityType.ILLUSIONER);
        //tag(monster).add(EntityType.MAGMA_CUBE);
        //tag(monster).add(EntityType.PHANTOM);
        //tag(monster).add(EntityType.PILLAGER);
        //tag(monster).add(EntityType.RAVAGER);
        //tag(monster).add(EntityType.SHULKER);
        //tag(monster).add(EntityType.SILVERFISH);
        //tag(monster).add(EntityType.SKELETON);
        //tag(monster).add(EntityType.SLIME);
        //tag(monster).add(EntityType.SPIDER);
        //tag(monster).add(EntityType.STRAY);
        //tag(monster).add(EntityType.VEX);
        //tag(monster).add(EntityType.VINDICATOR);
        //tag(monster).add(EntityType.WITCH);
        //tag(monster).add(EntityType.WITHER_SKELETON);
        //tag(monster).add(EntityType.ZOGLIN);
        //tag(monster).add(EntityType.ZOMBIE);
        //tag(monster).add(EntityType.ZOMBIE_VILLAGER);


        tag(animal).add(EntityType.ALLAY);
        tag(animal).add(EntityType.AXOLOTL);
        tag(animal).add(EntityType.BAT);
        tag(animal).add(EntityType.BEE);
        tag(animal).add(EntityType.CAT);
        tag(animal).add(EntityType.CHICKEN);
        tag(animal).add(EntityType.COD);
        tag(animal).add(EntityType.COW);
        tag(animal).add(EntityType.DONKEY);
        tag(animal).add(EntityType.DOLPHIN);
        tag(animal).add(EntityType.FOX);
        tag(animal).add(EntityType.GOAT);
        tag(animal).add(EntityType.HORSE);
        tag(animal).add(EntityType.LLAMA);
        tag(animal).add(EntityType.MOOSHROOM);
        tag(animal).add(EntityType.MULE);
        tag(animal).add(EntityType.OCELOT);
        tag(animal).add(EntityType.PANDA);
        tag(animal).add(EntityType.PARROT);
        tag(animal).add(EntityType.PIG);
        tag(animal).add(EntityType.POLAR_BEAR);
        tag(animal).add(EntityType.PUFFERFISH);
        tag(animal).add(EntityType.RABBIT);
        tag(animal).add(EntityType.SALMON);
        tag(animal).add(EntityType.SHEEP);
        tag(animal).add(EntityType.SQUID);
        tag(animal).add(EntityType.TROPICAL_FISH);
        tag(animal).add(EntityType.TURTLE);
        tag(animal).add(EntityType.WANDERING_TRADER);
        tag(animal).add(EntityType.WOLF);


        tag(fish).add(EntityType.COD);
        tag(fish).add(EntityType.PUFFERFISH);
        tag(fish).add(EntityType.SALMON);
        tag(fish).add(EntityType.TROPICAL_FISH);


        tag(illager).add(EntityType.EVOKER);
        tag(illager).add(EntityType.ILLUSIONER);
        tag(illager).add(EntityType.PILLAGER);
        tag(illager).add(EntityType.VINDICATOR);
    }
}
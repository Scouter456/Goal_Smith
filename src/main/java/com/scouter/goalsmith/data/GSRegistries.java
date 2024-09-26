package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import static com.scouter.goalsmith.Goalsmith.prefix;

public class GSRegistries {
    static { init(); }
    public static final Registry<Codec<? extends GoalOperation>> GOAL_OPERATION_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.GOAL_OPERATION_SERIALIZERS)
            .buildAndRegister();

    public static final Registry<Codec<? extends TargetGoalOperation>> TARGET_GOAL_OPERATION_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.TARGET_GOAL_OPERATION_SERIALIZERS)
            .buildAndRegister();

    public static final Registry<Codec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.GOAL_TYPE_SERIALIZERS)
            .buildAndRegister();

    public static final Registry<Codec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.TARGET_GOAL_TYPE_SERIALIZERS)
            .buildAndRegister();

    public static final Registry<Codec<? extends PredicateCodec<?>>> PREDICATE_TYPE_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.PREDICATE_TYPE_SERIALIZERS)
            .buildAndRegister();

    public static final Registry<Codec<? extends EntityTargetType>> ENTITY_TARGET_TYPE_TYPE_SERIALIZER = FabricRegistryBuilder.createSimple(Keys.ENTITY_TARGET_TYPE_SERIALIZERS)
            .buildAndRegister();

    //public static final DeferredRegister<Codec<? extends GoalOperation>> GOAL_OPERATION_SERIALIZER = DeferredRegister.create(Keys.GOAL_OPERATION_SERIALIZERS, Keys.GOAL_OPERATION_SERIALIZERS.location().getNamespace());
    //public static final Supplier<IForgeRegistry<Codec<? extends GoalOperation>>> GOAL_OPERATION_SERIALIZER_SUPPLIER = GOAL_OPERATION_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends GoalOperation>>().disableSaving().disableSync());

    //public static final DeferredRegister<Codec<? extends TargetGoalOperation>> TARGET_GOAL_OPERATION_SERIALIZER = DeferredRegister.create(Keys. TARGET_GOAL_OPERATION_SERIALIZERS, Keys. TARGET_GOAL_OPERATION_SERIALIZERS.location().getNamespace());
    //public static final Supplier<IForgeRegistry<Codec<? extends TargetGoalOperation>>>  TARGET_GOAL_OPERATION_SERIALIZER_SUPPLIER =  TARGET_GOAL_OPERATION_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends TargetGoalOperation>>().disableSaving().disableSync());


    //public static final DeferredRegister<Codec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = DeferredRegister.create(Keys.GOAL_TYPE_SERIALIZERS, Keys.GOAL_TYPE_SERIALIZERS.location().getNamespace());
    //public static final Supplier<IForgeRegistry<Codec<? extends GoalCodec>>> GOAL_TYPE_SERIALIZER_SUPPLIER = GOAL_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends GoalCodec>>().disableSaving().disableSync());

    //public static final DeferredRegister<Codec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = DeferredRegister.create(Keys.TARGET_GOAL_TYPE_SERIALIZERS, Keys.TARGET_GOAL_TYPE_SERIALIZERS.location().getNamespace());
    //public static final Supplier<IForgeRegistry<Codec<? extends TargetGoalCodec>>> TARGET_GOAL_TYPE_SERIALIZER_SUPPLIER = TARGET_GOAL_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends TargetGoalCodec>>().disableSaving().disableSync());

   //public static final DeferredRegister<Codec<? extends PredicateCodec<?>>> PREDICATE_TYPE_SERIALIZER = DeferredRegister.create(Keys.PREDICATE_TYPE_SERIALIZERS, Keys.PREDICATE_TYPE_SERIALIZERS.location().getNamespace());
   //public static final Supplier<IForgeRegistry<Codec<? extends PredicateCodec<?>>>> PREDICATE_TYPE_SERIALIZER_SUPPLIER = PREDICATE_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends PredicateCodec<?>>>().disableSaving().disableSync());

    //public static final DeferredRegister<Codec<? extends EntityTargetType>> ENTITY_TARGET_TYPE_TYPE_SERIALIZER = DeferredRegister.create(Keys.ENTITY_TARGET_TYPE_SERIALIZERS, Keys.ENTITY_TARGET_TYPE_SERIALIZERS.location().getNamespace());
    //public static final Supplier<IForgeRegistry<Codec<? extends EntityTargetType>>> ENTITY_TARGET_TYPE_TYPE_SERIALIZER_SUPPLIER = ENTITY_TARGET_TYPE_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends EntityTargetType>>().disableSaving().disableSync());


    public static final class Keys {

        public static final ResourceKey<Registry<Codec<? extends PredicateCodec<?>>>> PREDICATE_TYPE_SERIALIZERS = key(prefix("predicate_type_serializer").toString());
        public static final ResourceKey<Registry<PredicateCodec<?>>> PREDICATE_TYPE = key(prefix("predicate_type").toString());

        public static final ResourceKey<Registry<Codec<? extends GoalCodec>>> GOAL_TYPE_SERIALIZERS = key(prefix("goal_type_serializer").toString());
        public static final ResourceKey<Registry<GoalCodec>> GOAL_TYPE = key(prefix("goal_type").toString());

        public static final ResourceKey<Registry<Codec<? extends GoalOperation>>> GOAL_OPERATION_SERIALIZERS = key(prefix("goal_operation_serializer").toString());
        public static final ResourceKey<Registry<GoalOperation>> GOAL_OPERATION = key(prefix("goal_operation").toString());

        public static final ResourceKey<Registry<Codec<? extends TargetGoalOperation>>> TARGET_GOAL_OPERATION_SERIALIZERS = key(prefix("target_goal_operation_serializer").toString());
        public static final ResourceKey<Registry<TargetGoalOperation>> TARGET_GOAL_OPERATION = key(prefix("target_goal_operation").toString());

        public static final ResourceKey<Registry<Codec<? extends TargetGoalCodec>>> TARGET_GOAL_TYPE_SERIALIZERS = key(prefix("target_goal_type_serializer").toString());
        public static final ResourceKey<Registry<TargetGoalCodec>> TARGET_GOAL_TYPE = key(prefix("target_goal_type").toString());

        public static final ResourceKey<Registry<Codec<? extends EntityTargetType>>> ENTITY_TARGET_TYPE_SERIALIZERS = key(prefix("entity_target_type_serializer").toString());
        public static final ResourceKey<Registry<EntityTargetType>> ENTITY_TARGET_TYPE = key(prefix("entity_target_type").toString());

        private static <T> ResourceKey<Registry<T>> key(String name)
        {
            return ResourceKey.createRegistryKey(new ResourceLocation(name));
        }
        private static void init() {}

    }

    public static void init()
    {
        Keys.init();
    }
}
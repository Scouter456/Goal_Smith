package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class PMRegistries {
    static { init(); }
    public static final DeferredRegister<Codec<? extends GoalOperation>> GOAL_OPERATION_SERIALIZER = DeferredRegister.create(Keys.GOAL_OPERATION_SERIALIZERS, Keys.GOAL_OPERATION_SERIALIZERS.location().getNamespace());
    public static final Supplier<IForgeRegistry<Codec<? extends GoalOperation>>> GOAL_OPERATION_SERIALIZER_SUPPLIER = GOAL_OPERATION_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends GoalOperation>>().disableSaving().disableSync());

    public static final DeferredRegister<Codec<? extends TargetGoalOperation>> TARGET_GOAL_OPERATION_SERIALIZER = DeferredRegister.create(Keys. TARGET_GOAL_OPERATION_SERIALIZERS, Keys. TARGET_GOAL_OPERATION_SERIALIZERS.location().getNamespace());
    public static final Supplier<IForgeRegistry<Codec<? extends TargetGoalOperation>>>  TARGET_GOAL_OPERATION_SERIALIZER_SUPPLIER =  TARGET_GOAL_OPERATION_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends TargetGoalOperation>>().disableSaving().disableSync());


    public static final DeferredRegister<Codec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = DeferredRegister.create(Keys.GOAL_TYPE_SERIALIZERS, Keys.GOAL_TYPE_SERIALIZERS.location().getNamespace());
    public static final Supplier<IForgeRegistry<Codec<? extends GoalCodec>>> GOAL_TYPE_SERIALIZER_SUPPLIER = GOAL_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends GoalCodec>>().disableSaving().disableSync());

    public static final DeferredRegister<Codec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = DeferredRegister.create(Keys.TARGET_GOAL_TYPE_SERIALIZERS, Keys.TARGET_GOAL_TYPE_SERIALIZERS.location().getNamespace());
    public static final Supplier<IForgeRegistry<Codec<? extends TargetGoalCodec>>> TARGET_GOAL_TYPE_SERIALIZER_SUPPLIER = TARGET_GOAL_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends TargetGoalCodec>>().disableSaving().disableSync());

    public static final DeferredRegister<Codec<? extends PredicateCodec<?>>> PREDICATE_TYPE_SERIALIZER = DeferredRegister.create(Keys.PREDICATE_TYPE_SERIALIZERS, Keys.PREDICATE_TYPE_SERIALIZERS.location().getNamespace());
    public static final Supplier<IForgeRegistry<Codec<? extends PredicateCodec<?>>>> PREDICATE_TYPE_SERIALIZER_SUPPLIER = PREDICATE_TYPE_SERIALIZER.makeRegistry(() -> new RegistryBuilder<Codec<? extends PredicateCodec<?>>>().disableSaving().disableSync());



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
        private static <T> ResourceKey<Registry<T>> key(String name)
        {
            return ResourceKey.createRegistryKey(new ResourceLocation(name));
        }
        private static void init() {}

    }

    private static void init()
    {
        Keys.init();
    }
}
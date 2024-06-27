package com.scouter.goalsmith.data;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

import static com.scouter.goalsmith.GoalSmith.prefix;

public class PMRegistries {
    static { init(); }


    public static final Registry<MapCodec<? extends GoalOperation>> GOAL_OPERATION_SERIALIZER = new RegistryBuilder<>(Keys.GOAL_OPERATION_SERIALIZERS).maxId(Integer.MAX_VALUE - 1).sync(false).create();
    public static final Registry<MapCodec<? extends TargetGoalOperation>> TARGET_GOAL_OPERATION_SERIALIZER = new RegistryBuilder<>(Keys.TARGET_GOAL_OPERATION_SERIALIZERS).maxId(Integer.MAX_VALUE - 1).sync(false).create();
    public static final Registry<MapCodec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = new RegistryBuilder<>(Keys.GOAL_TYPE_SERIALIZERS).maxId(Integer.MAX_VALUE - 1).sync(false).create();
    public static final Registry<MapCodec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = new RegistryBuilder<>(Keys.TARGET_GOAL_TYPE_SERIALIZERS).maxId(Integer.MAX_VALUE - 1).sync(false).create();
    public static final Registry<MapCodec<? extends PredicateCodec<?>>> PREDICATE_TYPE_SERIALIZER = new RegistryBuilder<>(Keys.PREDICATE_TYPE_SERIALIZERS).maxId(Integer.MAX_VALUE - 1).sync(false).create();



    public static final class Keys {

        public static final ResourceKey<Registry<MapCodec<? extends PredicateCodec<?>>>> PREDICATE_TYPE_SERIALIZERS = key(prefix("predicate_type_serializer").toString());
        public static final ResourceKey<Registry<PredicateCodec<?>>> PREDICATE_TYPE = key(prefix("predicate_type").toString());

        public static final ResourceKey<Registry<MapCodec<? extends GoalCodec>>> GOAL_TYPE_SERIALIZERS = key(prefix("goal_type_serializer").toString());
        public static final ResourceKey<Registry<GoalCodec>> GOAL_TYPE = key(prefix("goal_type").toString());

        public static final ResourceKey<Registry<MapCodec<? extends GoalOperation>>> GOAL_OPERATION_SERIALIZERS = key(prefix("goal_operation_serializer").toString());
        public static final ResourceKey<Registry<GoalOperation>> GOAL_OPERATION = key(prefix("goal_operation").toString());

        public static final ResourceKey<Registry<MapCodec<? extends TargetGoalOperation>>> TARGET_GOAL_OPERATION_SERIALIZERS = key(prefix("target_goal_operation_serializer").toString());
        public static final ResourceKey<Registry<TargetGoalOperation>> TARGET_GOAL_OPERATION = key(prefix("target_goal_operation").toString());


        public static final ResourceKey<Registry<MapCodec<? extends TargetGoalCodec>>> TARGET_GOAL_TYPE_SERIALIZERS = key(prefix("target_goal_type_serializer").toString());
        public static final ResourceKey<Registry<TargetGoalCodec>> TARGET_GOAL_TYPE = key(prefix("target_goal_type").toString());
        private static <T> ResourceKey<Registry<T>> key(String name)
        {
            return ResourceKey.createRegistryKey( ResourceLocation.parse(name));
        }
        private static void init() {}

    }

    private static void init()
    {
        Keys.init();
    }
}
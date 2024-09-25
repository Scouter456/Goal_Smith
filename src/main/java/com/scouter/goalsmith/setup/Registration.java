package com.scouter.goalsmith.setup;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.data.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();



        GSRegistries.GOAL_TYPE_SERIALIZER.register(bus);
        GSRegistries.TARGET_GOAL_TYPE_SERIALIZER.register(bus);
        GSRegistries.PREDICATE_TYPE_SERIALIZER.register(bus);
        GSRegistries.GOAL_OPERATION_SERIALIZER.register(bus);
        GSRegistries.TARGET_GOAL_OPERATION_SERIALIZER.register(bus);
        GSRegistries.ENTITY_TARGET_TYPE_TYPE_SERIALIZER.register(bus);
        GoalRegistry.GOAL_TYPE_SERIALIZER.register(bus);
        GoalRegistry.TARGET_GOAL_TYPE_SERIALIZER.register(bus);
        PredicateRegistry.PREDICATE_SERIALIZER.register(bus);
        GoalOperationRegistry.GOAL_OPERATION.register(bus);
        GoalOperationRegistry.TARGET_GOAL_OPERATION.register(bus);
        EntityTargetTypeRegistry.ENTITY_TARGET_TYPE.register(bus);
    }
}

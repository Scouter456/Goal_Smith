package com.scouter.goalsmith.setup;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.data.GoalOperationRegistry;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import org.slf4j.Logger;


public class Registration {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void init(){

        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();



        //PMRegistries.GOAL_TYPE_SERIALIZER.register(bus);
        //PMRegistries.TARGET_GOAL_TYPE_SERIALIZER.register(bus);
        //PMRegistries.PREDICATE_TYPE_SERIALIZER.register(bus);
        //PMRegistries.GOAL_OPERATION_SERIALIZER.register(bus);
        //PMRegistries.TARGET_GOAL_OPERATION_SERIALIZER.register(bus);

        GoalRegistry.GOAL_TYPE_SERIALIZER.register(bus);
        GoalRegistry.TARGET_GOAL_TYPE_SERIALIZER.register(bus);
        PredicateRegistry.PREDICATE_SERIALIZER.register(bus);
        GoalOperationRegistry.GOAL_OPERATION.register(bus);
        GoalOperationRegistry.TARGET_GOAL_OPERATION.register(bus);

    }
}

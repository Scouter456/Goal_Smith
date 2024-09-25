package com.scouter.goalsmith.setup;

import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.GSRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = GoalSmith.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
    }

    public static void setup(){
    }

    @SubscribeEvent
    private static void registerRegistries(NewRegistryEvent event) {
        event.register(GSRegistries.GOAL_OPERATION_SERIALIZER);
        event.register(GSRegistries.TARGET_GOAL_OPERATION_SERIALIZER);
        event.register(GSRegistries.GOAL_TYPE_SERIALIZER);
        event.register(GSRegistries.TARGET_GOAL_TYPE_SERIALIZER);
        event.register(GSRegistries.PREDICATE_TYPE_SERIALIZER);
        event.register(GSRegistries.ENTITY_TARGET_TYPE_TYPE_SERIALIZER);
    }

}

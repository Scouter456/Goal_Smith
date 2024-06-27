package com.scouter.goalsmith.setup;

import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.PMRegistries;
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
        event.register(PMRegistries.GOAL_OPERATION_SERIALIZER);
        event.register(PMRegistries.TARGET_GOAL_OPERATION_SERIALIZER);
        event.register(PMRegistries.GOAL_TYPE_SERIALIZER);
        event.register(PMRegistries.TARGET_GOAL_TYPE_SERIALIZER);
        event.register(PMRegistries.PREDICATE_TYPE_SERIALIZER);

    }

}

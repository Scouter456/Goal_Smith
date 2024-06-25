package com.scouter.goalsmith.setup;

import com.scouter.goalsmith.GoalSmith;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = GoalSmith.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void init(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
        });
    }

    public static void setup(){
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }



    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event){
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeModificationEvent event){
        //for(EntityType<? extends LivingEntity> types : event.getTypes()) {
        //    for(Attribute attributes: BuiltInRegistries.ATTRIBUTE.stream().toList()) {
        //        if(!event.has(types, attributes)) {
        //            event.add(types, attributes, 0);
        //        }
        //    }
        //}
    }


}

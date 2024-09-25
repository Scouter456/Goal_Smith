package com.scouter.goalsmith.events;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.data.GoalData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.List;

@EventBusSubscriber(modid = GoalSmith.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new EntityGoalJsonManager());
    }

    @SubscribeEvent
    public static void onServerStart(ServerStartingEvent event) {
        EntityGoalJsonManager.applyGoalData(event.getServer().overworld());
    }




    @SubscribeEvent
    public static void spawnEvent(EntityJoinLevelEvent event) {
        if(event.getEntity() != null && event.getEntity() instanceof PathfinderMob mob && !event.getLevel().isClientSide) {
            ResourceLocation rl =  ResourceLocation.parse(mob.getEncodeId());
            EntityGoalJsonManager.applyGoalData((ServerLevel) event.getLevel());

            if(rl != null && EntityGoalJsonManager.getEntityData().containsKey(rl)) {


                List<GoalData> entityData = EntityGoalJsonManager.getEntityData().get(rl);
                for(GoalData data : entityData) {
                    data.performOperations(mob);
                }
            }

            List<GoalData> data = EntityGoalJsonManager.getAllEntityData();
            for(GoalData all : data) {
                all.performOperations(mob);
            }
        }
    }
}


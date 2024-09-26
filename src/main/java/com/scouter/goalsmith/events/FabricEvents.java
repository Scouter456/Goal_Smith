package com.scouter.goalsmith.events;

import com.scouter.goalsmith.data.EntityGoalJsonManager;
import com.scouter.goalsmith.data.GoalData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;

import java.util.List;

public class FabricEvents {

    public static void onServerStart() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> EntityGoalJsonManager.applyGoalData(server.overworld()));
    }

    public static void onEntityLoad() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, server) -> {
            if (entity != null && entity instanceof PathfinderMob mob && !server.isClientSide) {
                ResourceLocation rl = new ResourceLocation(mob.getEncodeId());
                EntityGoalJsonManager.applyGoalData(server);

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
        });
    }
}

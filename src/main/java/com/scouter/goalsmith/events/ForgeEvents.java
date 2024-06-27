package com.scouter.goalsmith.events;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import org.slf4j.Logger;

@EventBusSubscriber(modid = GoalSmith.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ForgeEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new EntityGoalJsonManager());
    }

    @SubscribeEvent
    public static void spawnEvent(EntityJoinLevelEvent event) {
        if(event.getEntity() != null && event.getEntity() instanceof PathfinderMob mob) {
            ResourceLocation rl =  ResourceLocation.parse(mob.getEncodeId());
            if(rl != null && EntityGoalJsonManager.getEntityData().containsKey(rl)) {


                GoalData entityData = EntityGoalJsonManager.getEntityData().get(rl);

                for(GoalOperation goalOperation : entityData.goalOperation()) {
                    goalOperation.performOperation(mob);
                }

                for(TargetGoalOperation targetGoalOperation : entityData.targetGoalOperation()) {
                    targetGoalOperation.performOperation(mob);
                }

                for(AttributeAdditions attributeAdditions : entityData.attributeAdditions()) {
                    attributeAdditions.performAdditions(mob);
                }
            }
        }
    }
}


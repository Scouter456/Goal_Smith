package com.scouter.goalsmith.events;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = GoalSmith.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onRegisterReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new EntityGoalJsonManager());
    }

    @SubscribeEvent
    public static void spawnEvent(EntityJoinLevelEvent event) {
        if(event.getEntity() != null && event.getEntity() instanceof PathfinderMob mob) {
            ResourceLocation rl = new ResourceLocation(mob.getEncodeId());
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


package com.scouter.goalsmith.events;

import com.mojang.logging.LogUtils;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.*;
import com.scouter.goalsmith.data.operation.goal.RemoveAllOperation;
import com.scouter.goalsmith.data.operation.goal.RemoveSpecificOperation;
import com.scouter.goalsmith.data.operation.goal.RemoveSpecificPriorityOperation;
import com.scouter.goalsmith.data.operation.target.RemoveAllTargetOperation;
import com.scouter.goalsmith.data.operation.target.RemoveSpecificTargetOperation;
import com.scouter.goalsmith.data.operation.target.RemoveSpecificTargetPriorityOperation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = GoalSmith.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
            ResourceLocation rl = new ResourceLocation(mob.getEncodeId());
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


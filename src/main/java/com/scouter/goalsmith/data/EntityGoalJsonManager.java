package com.scouter.goalsmith.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGoalJsonManager extends SimpleJsonResourceReloadListener {

    private static final Gson STANDARD_GSON = new Gson();
    public static final Logger LOGGER = LogManager.getLogger();
    private final String folderName;

    protected static Map<ResourceLocation, List<GoalData>> entityDataMap = new HashMap<>();
    protected static Map<String, GoalData> entityDataStringMap = new HashMap<>();
    protected static List<GoalData> goalData = new ArrayList<>();
    protected static List<GoalData> allEntityData = new ArrayList<>();
    public EntityGoalJsonManager() {
        this("goalsmith/goaldata", STANDARD_GSON);
    }

    public static Map<ResourceLocation, List<GoalData>> getEntityData() {
        return entityDataMap;
    }

    public static List<GoalData> getAllEntityData() {
        return allEntityData;
    }



    public EntityGoalJsonManager(String folderName, Gson gson) {
        super(gson, folderName);
        this.folderName = folderName;
    }

    public static void applyGoalData(ServerLevel level) {
        if(!entityDataMap.isEmpty() && !allEntityData.isEmpty() || goalData.isEmpty()) return;
        for(GoalData goals : goalData){
            goals.getEntityTargetType().apply(level, goals);
        }
        goalData.clear();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        entityDataStringMap.clear();
        entityDataMap.clear();
        allEntityData.clear();

        List<GoalData> goalDatas = new ArrayList<>();
        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();

            GoalData entityData = GoalData.TARGET_ENTITY_CODEC.decode(JsonOps.INSTANCE, element)
                    .getOrThrow()
                    .getFirst();
            goalDatas.add(entityData);
        }
        this.goalData = goalDatas;

        LOGGER.info("Data loader for {} loaded {} jsons", this.folderName, this.goalData.size());
    }
}

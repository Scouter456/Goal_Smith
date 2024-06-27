package com.scouter.goalsmith.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EntityGoalJsonManager extends SimpleJsonResourceReloadListener {

    private static final Gson STANDARD_GSON = new Gson();
    public static final Logger LOGGER = LogManager.getLogger();
    private final String folderName;

    protected static Map<ResourceLocation, GoalData> entityDataMap = new HashMap<>();


    public EntityGoalJsonManager() {
        this("goalsmith/goaldata", STANDARD_GSON);
    }

    public static Map<ResourceLocation, GoalData> getEntityData() {
        return entityDataMap;
    }

    public EntityGoalJsonManager(String folderName, Gson gson) {
        super(gson, folderName);
        this.folderName = folderName;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        Map<ResourceLocation, GoalData> puppets = new HashMap<>();
        for (Map.Entry<ResourceLocation, JsonElement> entry : jsons.entrySet()) {
            ResourceLocation key = entry.getKey();
            JsonElement element = entry.getValue();
            GoalData.CODEC.decode(JsonOps.INSTANCE, element)
                    .get()
                    .ifLeft(result -> {
                        GoalData entityData = result.getFirst();
                        puppets.put(entityData.targetEntity(), entityData);
                    })
                    .ifRight(partial -> LOGGER.error("Failed to parse goal data JSON for {} due to: {}", key, partial.message()));
        }

        this.entityDataMap = puppets;
        LOGGER.info("Data loader for {} loaded {} jsons", this.folderName, this.entityDataMap.size());
    }
}

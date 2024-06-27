package com.scouter.goalsmith.datagen;

import com.google.common.collect.Sets;
import com.mojang.serialization.JsonOps;
import com.scouter.goalsmith.data.GoalData;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.scouter.goalsmith.GoalSmith.prefix;

public abstract class GoalDataProvider implements DataProvider {
    protected final PackOutput.PathProvider entityPathProvider;

    public GoalDataProvider(PackOutput pOutput, String modid) {
        this.entityPathProvider = pOutput.createPathProvider(PackOutput.Target.DATA_PACK, new ResourceLocation(modid,"goalsmith/goaldata").getPath());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        Set<ResourceLocation> set = Sets.newHashSet();
        Set<ResourceLocation> taskSet = Sets.newHashSet();
        List<CompletableFuture<?>> list = new ArrayList<>();
        this.createGoalData((entity -> {
            if (!set.add(entity.name())) {
                throw new IllegalStateException("Duplicate Goal " + entity.name());
            } else {

                GoalData.CODEC.encodeStart(JsonOps.INSTANCE, entity.data())
                        .get()
                        .ifLeft(e -> list.add(DataProvider.saveStable(pOutput, e, this.entityPathProvider.json(entity.name()))))
                        .ifRight(partial -> LOGGER.error("Failed to create goalData {}", entity.data()));

            }
        }));
        return CompletableFuture.allOf(list.toArray((p_253414_) -> {
            return new CompletableFuture[p_253414_];
        }));
    }


    protected abstract void createGoalData(Consumer<GoalDataConsumer> pWriter);
    public record GoalDataConsumer(ResourceLocation name, GoalData data) {
    }
    @Override
    public String getName() {
        return "GoalData";
    }
}

package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record GoalData(ResourceLocation targetEntity, List<GoalOperation> goalOperation, List<TargetGoalOperation> targetGoalOperation, List<AttributesAdditions> attributeAdditions) {
    public static final Codec<GoalData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("target_entity").forGetter(GoalData::targetEntity),
            GoalOperation.DIRECT_CODEC.listOf().fieldOf("goal_operations").forGetter(GoalData::goalOperation),
            TargetGoalOperation.DIRECT_CODEC.listOf().fieldOf("target_goal_operations").forGetter(GoalData::targetGoalOperation),
            AttributesAdditions.CODEC.listOf().fieldOf("attributes_additions").forGetter(GoalData::attributeAdditions)

    ).apply(instance, GoalData::new));
}

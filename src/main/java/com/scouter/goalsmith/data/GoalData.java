package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;

public record GoalData(ResourceLocation targetEntity, List<GoalOperation> goalOperation, List<TargetGoalOperation> targetGoalOperation, List<AttributesAdditions> attributeAdditions) {
    public static final Codec<GoalData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("target_entity").forGetter(GoalData::targetEntity),
            NullableFieldCodec.makeDefaultableField("goal_operations", GoalOperation.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::goalOperation),
            NullableFieldCodec.makeDefaultableField("target_goal_operations", TargetGoalOperation.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::targetGoalOperation),
            NullableFieldCodec.makeDefaultableField("attributes_additions", AttributesAdditions.CODEC.listOf(), Collections.emptyList()).forGetter(GoalData::attributeAdditions)

    ).apply(instance, GoalData::new));
}

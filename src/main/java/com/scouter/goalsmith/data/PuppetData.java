package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class PuppetData
{
    public static final Codec<PuppetData> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(
                    ResourceLocation.CODEC.fieldOf("modelLoc").forGetter(PuppetData::getModelLoc),
                    ResourceLocation.CODEC.listOf().fieldOf("textureLoc").forGetter(PuppetData::getTextureLoc),
                    ResourceLocation.CODEC.listOf().fieldOf("animLoc").forGetter(PuppetData::getAnimLoc),
                    Codec.FLOAT.fieldOf("width").forGetter(PuppetData::getWidth),
                    Codec.FLOAT.fieldOf("height").forGetter(PuppetData::getHeight),
                    NullableFieldCodec.makeDefaultableField("goals", GoalCodec.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(PuppetData::getGoals),
                    NullableFieldCodec.makeDefaultableField("target_goals", TargetGoalCodec.DIRECT_CODEC.listOf(), Collections.emptyList()).forGetter(PuppetData::getTargetGoals)
            ).apply(inst, PuppetData::new)
    );

    private final ResourceLocation modelLoc;
    private final List<ResourceLocation> textureLoc;
    private final List<ResourceLocation> animLoc;
    private final List<GoalCodec> goals;
    private final List<TargetGoalCodec> targetGoals;
    private final float width;
    private final float height;

    public PuppetData(ResourceLocation modelLoc, List<ResourceLocation> textureLoc, List<ResourceLocation> animLoc, float width, float height, List<GoalCodec> goals, List<TargetGoalCodec> targetGoals) {
        this.modelLoc = modelLoc;
        this.textureLoc = textureLoc;
        this.animLoc = animLoc;
        this.width = width;
        this.height = height;
        this.goals = goals;
        this.targetGoals = targetGoals;
    }

    public ResourceLocation getModelLoc() {
        return modelLoc;
    }

    public List<ResourceLocation> getTextureLoc() {
        return textureLoc;
    }

    public List<ResourceLocation> getAnimLoc() {
        return animLoc;
    }

    public List<GoalCodec> getGoals() {
        return goals;
    }
    public List<TargetGoalCodec> getTargetGoals() {
        return targetGoals;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}

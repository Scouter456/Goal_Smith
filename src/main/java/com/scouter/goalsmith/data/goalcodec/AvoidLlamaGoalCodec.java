package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.entityspecific.wolf.AvoidLlamaGoal;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class AvoidLlamaGoalCodec implements GoalCodec {

    public static final MapCodec<AvoidLlamaGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(AvoidLlamaGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("entity_class_to_avoid").forGetter(AvoidLlamaGoalCodec::getEntityClassToAvoid),
            Codec.FLOAT.fieldOf("max_distance").forGetter(AvoidLlamaGoalCodec::getMaxDistance),
            Codec.DOUBLE.fieldOf("walk_speed_modifier").forGetter(AvoidLlamaGoalCodec::getWalkSpeedModifier),
            Codec.DOUBLE.fieldOf("sprint_speed_modifier").forGetter(AvoidLlamaGoalCodec::getSprintSpeedModifier)
    ).apply(instance, AvoidLlamaGoalCodec::new));

    private final int goalPriority;
    private final TagKey<EntityType<?>> entityClassToAvoid;
    private final float maxDistance;
    private final double walkSpeedModifier;
    private final double sprintSpeedModifier;

    public AvoidLlamaGoalCodec(int goalPriority, TagKey<EntityType<?>> entityClassToAvoid, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier) {
        this.goalPriority = goalPriority;
        this.entityClassToAvoid = entityClassToAvoid;
        this.maxDistance = maxDistance;
        this.walkSpeedModifier = walkSpeedModifier;
        this.sprintSpeedModifier = sprintSpeedModifier;
    }



    public int getGoalPriority() {
        return goalPriority;
    }

    public TagKey<EntityType<?>> getEntityClassToAvoid() {
        return entityClassToAvoid;
    }


    public float getMaxDistance() {
        return maxDistance;
    }

    public double getWalkSpeedModifier() {
        return walkSpeedModifier;
    }

    public double getSprintSpeedModifier() {
        return sprintSpeedModifier;
    }


    @Override
    public Goal addGoal(PathfinderMob mob) {
        AvoidLlamaGoal goal = new AvoidLlamaGoal(mob, entityClassToAvoid, maxDistance, walkSpeedModifier, sprintSpeedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.AVOID_LLAMA_GOAL.get();
    }

    @Override
    public String toString() {
        return "AvoidLlamaGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", entityClassToAvoid=" + entityClassToAvoid +
                ", maxDistance=" + maxDistance +
                ", walkSpeedModifier=" + walkSpeedModifier +
                ", sprintSpeedModifier=" + sprintSpeedModifier +
                '}';
    }
}
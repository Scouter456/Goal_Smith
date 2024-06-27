package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.LookAtEntityGoalImproved;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class LookAtEntityGoalCodec implements GoalCodec {

    public static final MapCodec<LookAtEntityGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(LookAtEntityGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("look_at_type").forGetter(LookAtEntityGoalCodec::getLookAtType),
            Codec.FLOAT.fieldOf("look_distance").forGetter(LookAtEntityGoalCodec::getLookDistance),
            NullableFieldCodec.makeDefaultableField("probability",Codec.FLOAT, 0.02F).forGetter(LookAtEntityGoalCodec::getProbability),
            NullableFieldCodec.makeDefaultableField("only_horizontal",Codec.BOOL,false).forGetter(LookAtEntityGoalCodec::isOnlyHorizontal)
    ).apply(instance, LookAtEntityGoalCodec::new));

    private final int goalPriority;
    private final TagKey<EntityType<?>> lookAtType;
    private final float lookDistance;
    private final float probability;
    private final boolean onlyHorizontal;

    public LookAtEntityGoalCodec(int goalPriority, TagKey<EntityType<?>> lookAtType, float lookDistance, float probability, boolean onlyHorizontal) {
        this.goalPriority = goalPriority;
        this.lookAtType = lookAtType;
        this.lookDistance = lookDistance;
        this.probability = probability;
        this.onlyHorizontal = onlyHorizontal;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public TagKey<EntityType<?>> getLookAtType() {
        return lookAtType;
    }

    public float getLookDistance() {
        return lookDistance;
    }

    public float getProbability() {
        return probability;
    }

    public boolean isOnlyHorizontal() {
        return onlyHorizontal;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        LookAtEntityGoalImproved goal = new LookAtEntityGoalImproved(mob, lookAtType, lookDistance, probability, onlyHorizontal);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.LOOK_AT_ENTITY_GOAL.get();
    }

    @Override
    public String toString() {
        return "LookAtPlayerGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", lookAtType=" + lookAtType +
                ", lookDistance=" + lookDistance +
                ", probability=" + probability +
                ", onlyHorizontal=" + onlyHorizontal +
                '}';
    }
}

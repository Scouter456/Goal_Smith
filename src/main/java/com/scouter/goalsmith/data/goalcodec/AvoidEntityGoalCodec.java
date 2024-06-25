package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.goals.AvoidEntityGoalImproved;
import com.scouter.goalsmith.data.predicates.NoCreativeOrSpectatorPredicate;
import com.scouter.goalsmith.data.predicates.TruePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.function.Predicate;

public class AvoidEntityGoalCodec implements GoalCodec {

    public static final Codec<AvoidEntityGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(AvoidEntityGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("entity_class_to_avoid").forGetter(AvoidEntityGoalCodec::getEntityClassToAvoid),
            NullableFieldCodec.makeDefaultableField("avoid_predicate", PredicateCodec.DIRECT_CODEC, new TruePredicate<>()).forGetter(AvoidEntityGoalCodec::getAvoidPredicate),
            Codec.FLOAT.fieldOf("max_distance").forGetter(AvoidEntityGoalCodec::getMaxDistance),
            Codec.DOUBLE.fieldOf("walk_speed_modifier").forGetter(AvoidEntityGoalCodec::getWalkSpeedModifier),
            Codec.DOUBLE.fieldOf("sprint_speed_modifier").forGetter(AvoidEntityGoalCodec::getSprintSpeedModifier),
            NullableFieldCodec.makeDefaultableField("predicate_on_avoid_entity", PredicateCodec.DIRECT_CODEC, new NoCreativeOrSpectatorPredicate()).forGetter(AvoidEntityGoalCodec::getPredicateOnAvoidEntity)
    ).apply(instance, (integer, aClass, predicateCodec, aFloat, aDouble, aDouble2, predicateCodec2) -> new AvoidEntityGoalCodec(integer, aClass, (PredicateCodec<Entity>) predicateCodec, aFloat, aDouble, aDouble2, (PredicateCodec<Entity>)predicateCodec2)));

    private final int goalPriority;
    private final TagKey<EntityType<?>> entityClassToAvoid;
    private PredicateCodec<Entity> avoidPredicate;
    private final float maxDistance;
    private final double walkSpeedModifier;
    private final double sprintSpeedModifier;
    private PredicateCodec<Entity> predicateOnAvoidEntity;

    public AvoidEntityGoalCodec(int goalPriority, TagKey<EntityType<?>> entityClassToAvoid, PredicateCodec<Entity> avoidPredicate, float maxDistance, double walkSpeedModifier, double sprintSpeedModifier, PredicateCodec<Entity> predicateOnAvoidEntity) {
        this.goalPriority = goalPriority;
        this.entityClassToAvoid = entityClassToAvoid;
        this.avoidPredicate = avoidPredicate;
        this.maxDistance = maxDistance;
        this.walkSpeedModifier = walkSpeedModifier;
        this.sprintSpeedModifier = sprintSpeedModifier;
        this.predicateOnAvoidEntity = predicateOnAvoidEntity;
    }



    public int getGoalPriority() {
        return goalPriority;
    }

    public TagKey<EntityType<?>> getEntityClassToAvoid() {
        return entityClassToAvoid;
    }

    public PredicateCodec<Entity> getAvoidPredicate() {
        return avoidPredicate;
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

    public PredicateCodec<Entity> getPredicateOnAvoidEntity() {
        return predicateOnAvoidEntity;
    }

    public Predicate<LivingEntity> avoidLivingEntityPredicate =  entity -> entity instanceof LivingEntity && avoidPredicate.getPredicate().test(entity);
    public Predicate<LivingEntity> predicateOnAvoidLivingEntity = entity -> entity instanceof LivingEntity && predicateOnAvoidEntity.getPredicate().test(entity);

    @Override
    public Goal addGoal(PathfinderMob mob) {
        AvoidEntityGoalImproved goal = new AvoidEntityGoalImproved(mob, entityClassToAvoid,  avoidLivingEntityPredicate, maxDistance, walkSpeedModifier, sprintSpeedModifier, predicateOnAvoidLivingEntity);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.AVOID_ENTITY_GOAL.get();
    }

    @Override
    public String toString() {
        return "AvoidEntityGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", entityClassToAvoid=" + entityClassToAvoid +
                ", avoidPredicate=" + avoidPredicate +
                ", maxDistance=" + maxDistance +
                ", walkSpeedModifier=" + walkSpeedModifier +
                ", sprintSpeedModifier=" + sprintSpeedModifier +
                ", predicateOnAvoidEntity=" + predicateOnAvoidEntity +
                '}';
    }
}

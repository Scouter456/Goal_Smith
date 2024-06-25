package com.scouter.goalsmith.data.goalcodec.targetgoalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.TargetGoalCodec;
import com.scouter.goalsmith.data.goals.target.NoneTameRandomTargetGoalImproved;
import com.scouter.goalsmith.data.predicates.TruePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class NonTameRandomTargetGoalCodec implements TargetGoalCodec {
    public static final Codec<NonTameRandomTargetGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("target_goal_priority").forGetter(codec -> codec.goalPriority),
            TagKey.codec(Registries.ENTITY_TYPE).fieldOf("target_type").forGetter(codec -> codec.targetType),
            NullableFieldCodec.makeDefaultableField("random_interval", Codec.INT, 10).forGetter(codec -> codec.randomInterval),
            Codec.BOOL.fieldOf("must_see").forGetter(codec -> codec.mustSee),
            NullableFieldCodec.makeDefaultableField("must_reach", Codec.BOOL, false).forGetter(codec -> codec.mustReach),
            NullableFieldCodec.makeDefaultableField("target_predicate", PredicateCodec.DIRECT_CODEC, new TruePredicate<>()).forGetter(c -> c.targetPredicate)
    ).apply(instance, (integer, entityTypeTagKey, integer2, aBoolean, aBoolean2, predicateCodec) -> new NonTameRandomTargetGoalCodec(integer, entityTypeTagKey, integer2, aBoolean, aBoolean2, (PredicateCodec<Entity>) predicateCodec)));
    private final int goalPriority;
    private final TagKey<EntityType<?>> targetType;
    private final int randomInterval;
    private final boolean mustSee;
    private final boolean mustReach;
    @Nullable
    private PredicateCodec<Entity> targetPredicate;

    public NonTameRandomTargetGoalCodec(int goalPriority, TagKey<EntityType<?>> targetType, int randomInterval, boolean mustSee, boolean mustReach, @Nullable PredicateCodec<Entity> targetPredicate) {
        this.goalPriority = goalPriority;
        this.targetType = targetType;
        this.randomInterval = randomInterval;
        this.mustSee = mustSee;
        this.mustReach = mustReach;
        this.targetPredicate = targetPredicate;
    }

    private final Predicate<LivingEntity> targetLivingEntityPredicate = entity -> entity instanceof LivingEntity && targetPredicate.getPredicate().test(entity);

    @Override
    public Goal addTargetGoal(PathfinderMob mob) {
        if(mob instanceof TamableAnimal animal) {
            NoneTameRandomTargetGoalImproved goal = new NoneTameRandomTargetGoalImproved(animal, targetType, randomInterval, mustSee, mustReach, targetLivingEntityPredicate);
            mob.targetSelector.addGoal(goalPriority, goal);
            return goal;
        }
        throw new RuntimeException("Tried adding NoneTameRandomTargetGoal to non TamableAnimal");
    }

    @Override
    public Codec<? extends TargetGoalCodec> codec() {
        return GoalRegistry.NON_TAME_RANDOM_TARGET_GOAL.get();
    }

    @Override
    public String toString() {
        return "NearestAttackableTargetGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", targetType=" + targetType +
                ", randomInterval=" + randomInterval +
                ", mustSee=" + mustSee +
                ", mustReach=" + mustReach +
                ", targetPredicate=" + targetPredicate +
                '}';
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.goals.PanicGoalImproved;
import com.scouter.goalsmith.data.predicates.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class PanicGoalCodec implements GoalCodec {

    public static final MapCodec<PanicGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(PanicGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(PanicGoalCodec::getSpeedModifier),
            NullableFieldCodec.makeDefaultableField("panic_predicate", PredicateCodec.DIRECT_CODEC, new OrPredicate<>(new OrPredicate<>(new NegatePredicate<>(new LastHurtByMobIsNullPredicate()), new IsFreezingPredicate()),new IsOnFirePredicate())).forGetter(PanicGoalCodec::getPredicateCodec)
    ).apply(instance, (integer, aDouble, predicateCodec1) ->  new PanicGoalCodec(integer, aDouble, (PredicateCodec<Entity>) predicateCodec1)));




    private final int goalPriority;
    private final double speedModifier;
    private final PredicateCodec<Entity> predicateCodec;

    public PanicGoalCodec(int goalPriority, double speedModifier, PredicateCodec<Entity> panicPredicate) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.predicateCodec = panicPredicate;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public PredicateCodec<Entity> getPredicateCodec() {
        return predicateCodec;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        PanicGoalImproved goal = new PanicGoalImproved(mob, speedModifier, predicateCodec.getPredicate());
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.PANIC_GOAL.get();
    }

    @Override
    public String toString() {
        return "PanicGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                "predicate=" + predicateCodec +
                '}';
    }
}
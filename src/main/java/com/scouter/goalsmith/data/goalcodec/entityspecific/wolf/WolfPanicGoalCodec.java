package com.scouter.goalsmith.data.goalcodec.entityspecific.wolf;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import com.scouter.goalsmith.data.goalcodec.PanicGoalCodec;
import com.scouter.goalsmith.data.predicates.IsFreezingPredicate;
import com.scouter.goalsmith.data.predicates.IsOnFirePredicate;
import com.scouter.goalsmith.data.predicates.OrPredicate;
import net.minecraft.world.entity.Entity;

public class WolfPanicGoalCodec extends PanicGoalCodec {

    public static final Codec<WolfPanicGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(PanicGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(PanicGoalCodec::getSpeedModifier),
            NullableFieldCodec.makeDefaultableField("panic_predicate", PredicateCodec.DIRECT_CODEC, new OrPredicate<>(new IsFreezingPredicate(),new IsOnFirePredicate())).forGetter(PanicGoalCodec::getPredicateCodec)
    ).apply(instance, (integer, aDouble, predicateCodec1) ->  new WolfPanicGoalCodec(integer, aDouble, (PredicateCodec<Entity>) predicateCodec1)));

    public WolfPanicGoalCodec(int goalPriority, double speedModifier, PredicateCodec<Entity> panicPredicate) {
        super(goalPriority, speedModifier, panicPredicate);
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.WOLF_PANIC_GOAL.get();
    }

}

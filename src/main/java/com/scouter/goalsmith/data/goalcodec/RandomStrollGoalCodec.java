package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;

public class RandomStrollGoalCodec implements GoalCodec {


    public static final Codec<RandomStrollGoalCodec> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.INT.fieldOf("goal_priority").forGetter(codec -> codec.goalPriority),
            NullableFieldCodec.makeDefaultableField("speed_modifier" ,Codec.DOUBLE, 1.0D).forGetter(codec -> codec.speedModifier),
            NullableFieldCodec.makeDefaultableField("interval",Codec.INT, 120).forGetter(codec -> codec.interval),
            NullableFieldCodec.makeDefaultableField("check_no_action_time", Codec.BOOL, true).forGetter(codec -> codec.checkNoActionTime)
    ).apply(builder, RandomStrollGoalCodec::new));

    private final double speedModifier;
    private final int goalPriority;
    private final int interval;
    private final boolean checkNoActionTime;

    public RandomStrollGoalCodec(int goalPriority, double speedModifier, int interval, boolean checkNoActionTime) {
        this.speedModifier = speedModifier;
        this.goalPriority = goalPriority;
        this.interval = interval;
        this.checkNoActionTime = checkNoActionTime;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RandomStrollGoal goal = new RandomStrollGoal(mob, speedModifier);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.RANDOM_STROLL_GOAL.get();
    }

    @Override
    public String toString() {
        return "RandomStrollGoalCodec{" +
                "speedModifier=" + speedModifier +
                ", goalPriority=" + goalPriority +
                '}';
    }
}

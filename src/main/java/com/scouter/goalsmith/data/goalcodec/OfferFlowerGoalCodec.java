package com.scouter.goalsmith.data.goalcodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.OfferFlowerGoalImproved;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class OfferFlowerGoalCodec implements GoalCodec {

    public static final Codec<OfferFlowerGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(OfferFlowerGoalCodec::getGoalPriority)
    ).apply(instance, OfferFlowerGoalCodec::new));

    private final int goalPriority;

    public OfferFlowerGoalCodec(int goalPriority) {
        this.goalPriority = goalPriority;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        OfferFlowerGoalImproved goal = new OfferFlowerGoalImproved(mob);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.OFFER_FLOWER_GOAL.get();
    }

    @Override
    public String toString() {
        return "OfferFlowerGoalImprovedCodec{" +
                "goalPriority=" + goalPriority +
                '}';
    }
}

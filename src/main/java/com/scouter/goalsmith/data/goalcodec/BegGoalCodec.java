package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.BegGoalImproved;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;

public class BegGoalCodec implements GoalCodec {


    public static final Codec<BegGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(BegGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ITEM).fieldOf("interesting_food").forGetter(BegGoalCodec::getFood),
            Codec.FLOAT.fieldOf("look_distance").forGetter(BegGoalCodec::getLookDistance)
                ).apply(instance, BegGoalCodec::new));

    private final int goalPriority;
    private final TagKey<Item> food;
    private final float lookDistance;


    public BegGoalCodec(int goalPriority, TagKey<Item> food, float lookDistance) {
        this.goalPriority = goalPriority;
        this.food = food;
        this.lookDistance = lookDistance;
    }

    private  int getGoalPriority() {
        return goalPriority;
    }
    private  TagKey<Item> getFood() {
        return food;
    }
    private float getLookDistance() {
        return lookDistance;
    }
    @Override
    public Goal addGoal(PathfinderMob mob) {
        BegGoalImproved goal = new BegGoalImproved(mob, food, lookDistance);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.BEG_GOAL.get();
    }
}

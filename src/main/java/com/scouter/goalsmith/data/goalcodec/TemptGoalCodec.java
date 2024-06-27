package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class TemptGoalCodec implements GoalCodec {

    public static final MapCodec<TemptGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(TemptGoalCodec::getGoalPriority),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(TemptGoalCodec::getSpeedModifier),
            TagKey.codec(Registries.ITEM).fieldOf("items").forGetter(TemptGoalCodec::getItems),
            Codec.BOOL.fieldOf("can_scare").forGetter(TemptGoalCodec::canScare)
    ).apply(instance, TemptGoalCodec::new));

    private final int goalPriority;
    private final double speedModifier;
    private final TagKey<Item> items;
    private final boolean canScare;

    public TemptGoalCodec(int goalPriority, double speedModifier, TagKey<Item> items, boolean canScare) {
        this.goalPriority = goalPriority;
        this.speedModifier = speedModifier;
        this.items = items;
        this.canScare = canScare;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public TagKey<Item> getItems() {
        return items;
    }

    public boolean canScare() {
        return canScare;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        TemptGoal goal = new TemptGoal(mob, speedModifier, Ingredient.of(items), canScare);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.TEMPT_GOAL.get();
    }

    @Override
    public String toString() {
        return "TemptGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", speedModifier=" + speedModifier +
                ", items=" + items +
                ", canScare=" + canScare +
                '}';
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.SearchForItemsGoal;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;

public class SearchForItemsGoalCodec implements GoalCodec {

    public static final MapCodec<SearchForItemsGoalCodec> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(SearchForItemsGoalCodec::getGoalPriority),
            TagKey.codec(Registries.ITEM).fieldOf("allowed_items").forGetter(SearchForItemsGoalCodec::getAllowedItems)
    ).apply(instance, SearchForItemsGoalCodec::new));

    private final int goalPriority;
    private final TagKey<Item> allowedItems;

    public SearchForItemsGoalCodec(int goalPriority, TagKey<Item> allowedItems) {
        this.goalPriority = goalPriority;
        this.allowedItems = allowedItems;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public TagKey<Item> getAllowedItems() {
        return allowedItems;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        SearchForItemsGoal goal = new SearchForItemsGoal(mob,allowedItems);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public MapCodec<? extends GoalCodec> codec() {
        return GoalRegistry.SEARCH_FOR_ITEMS_GOAL.get();
    }

    @Override
    public String toString() {
        return "SearchForItemsGoalCodec{" +
                "goalPriority=" + goalPriority +
                ", allowedItems=" + allowedItems +
                '}';
    }
}

package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.level.block.Block;

public class RemoveBlockGoalCodec implements GoalCodec {
    public static final Codec<RemoveBlockGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(RemoveBlockGoalCodec::getGoalPriority),
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block_to_remove").forGetter(RemoveBlockGoalCodec::getBlockToRemove),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(RemoveBlockGoalCodec::getSpeedModifier),
            Codec.INT.fieldOf("search_range").forGetter(RemoveBlockGoalCodec::getSearchRange)
    ).apply(instance, RemoveBlockGoalCodec::new));

    private final int goalPriority;
    private final Block blockToRemove;
    private final double speedModifier;
    private final int searchRange;

    public RemoveBlockGoalCodec(int goalPriority, Block blockToRemove, double speedModifier, int searchRange) {
        this.goalPriority = goalPriority;
        this.blockToRemove = blockToRemove;
        this.speedModifier = speedModifier;
        this.searchRange = searchRange;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public Block getBlockToRemove() {
        return blockToRemove;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public int getSearchRange() {
        return searchRange;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        RemoveBlockGoal goal = new RemoveBlockGoal(blockToRemove, mob, speedModifier, searchRange);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.REMOVE_BLOCK_GOAL.get();
    }


}
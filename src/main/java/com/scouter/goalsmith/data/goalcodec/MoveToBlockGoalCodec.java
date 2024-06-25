package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.goals.MoveToBlockGoalImproved;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;

public class MoveToBlockGoalCodec implements GoalCodec {

    public static final Codec<MoveToBlockGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("goal_priority").forGetter(MoveToBlockGoalCodec::getGoalPriority),
            TagKey.codec(Registries.BLOCK).fieldOf("block").forGetter(MoveToBlockGoalCodec::getBlock),
            Codec.DOUBLE.fieldOf("speed_modifier").forGetter(MoveToBlockGoalCodec::getSpeedModifier),
            Codec.INT.fieldOf("search_range").forGetter(MoveToBlockGoalCodec::getSearchRange),
            NullableFieldCodec.makeDefaultableField("vertical_search_range",Codec.INT, 1).forGetter(MoveToBlockGoalCodec::getVerticalSearchRange)
    ).apply(instance, MoveToBlockGoalCodec::new));

    private final int goalPriority;
    private final TagKey<Block> block;
    private final double speedModifier;
    private final int searchRange;
    private final int verticalSearchRange;

    public MoveToBlockGoalCodec(int goalPriority, TagKey<Block> block, double speedModifier, int searchRange, int verticalSearchRange) {
        this.goalPriority = goalPriority;
        this.block = block;
        this.speedModifier = speedModifier;
        this.searchRange = searchRange;
        this.verticalSearchRange = verticalSearchRange;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public TagKey<Block> getBlock() {
        return block;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public int getSearchRange() {
        return searchRange;
    }

    public int getVerticalSearchRange() {
        return verticalSearchRange;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        MoveToBlockGoalImproved goal = new MoveToBlockGoalImproved(mob, block, speedModifier, searchRange, verticalSearchRange);
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.MOVE_TO_BLOCK_GOAL.get();
    }

    @Override
    public String toString() {
        return "MoveToBlockGoalImprovedCodec{" +
                "goalPriority=" + goalPriority +
                ", block=" + block +
                ", speedModifier=" + speedModifier +
                ", searchRange=" + searchRange +
                ", verticalSearchRange=" + verticalSearchRange +
                '}';
    }
}

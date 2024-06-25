package com.scouter.goalsmith.data.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;

public class MoveToBlockGoalImproved extends MoveToBlockGoal {
    private final TagKey<Block> block;

    public MoveToBlockGoalImproved(PathfinderMob pMob, TagKey<Block> block, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
        super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        this.block = block;
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos).is(block);
    }
}

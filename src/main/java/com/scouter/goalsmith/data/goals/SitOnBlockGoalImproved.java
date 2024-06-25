package com.scouter.goalsmith.data.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class SitOnBlockGoalImproved extends MoveToBlockGoal {
    private final TamableAnimal animal;

    public SitOnBlockGoalImproved(TamableAnimal pCat, double pSpeedModifier) {
        super(pCat, pSpeedModifier, 8);
        this.animal = pCat;
    }

    public boolean canUse() {
        return this.animal.isTame() && !this.animal.isOrderedToSit() && super.canUse();
    }

    public void start() {
        super.start();
        this.animal.setInSittingPose(false);
    }

    public void stop() {
        super.stop();
        this.animal.setInSittingPose(false);
    }

    public void tick() {
        super.tick();
        this.animal.setInSittingPose(this.isReachedTarget());
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (!pLevel.isEmptyBlock(pPos.above())) {
            return false;
        } else {
            BlockState blockstate = pLevel.getBlockState(pPos);
            if (blockstate.is(Blocks.CHEST)) {
                return ChestBlockEntity.getOpenCount(pLevel, pPos) < 1;
            } else {
                return blockstate.is(Blocks.FURNACE) && (Boolean)blockstate.getValue(FurnaceBlock.LIT) ? true : blockstate.is(BlockTags.BEDS, (p_25156_) -> {
                    return (Boolean)p_25156_.getOptionalValue(BedBlock.PART).map((p_148084_) -> {
                        return p_148084_ != BedPart.HEAD;
                    }).orElse(true);
                });
            }
        }
    }
}

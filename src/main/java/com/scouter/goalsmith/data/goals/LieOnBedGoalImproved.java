package com.scouter.goalsmith.data.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.LevelReader;

import java.util.EnumSet;

public class LieOnBedGoalImproved extends MoveToBlockGoal {
    private final TamableAnimal mob;

    public LieOnBedGoalImproved(TamableAnimal pCat, double pSpeedModifier, int pSearchRange) {
        super(pCat, pSpeedModifier, pSearchRange, 6);
        this.mob = pCat;
        this.verticalSearchStart = -2;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    public boolean canUse() {
        return this.mob.isTame() && !this.mob.isOrderedToSit() && super.canUse() ||
                this.mob.isTame() && !this.mob.isOrderedToSit() && this.mob instanceof Cat cat && !cat.isLying() && super.canUse();
    }

    public void start() {
        super.start();
        this.mob.setInSittingPose(false);
    }

    protected int nextStartTick(PathfinderMob pCreature) {
        return 40;
    }

    public void stop() {
        super.stop();
        if(this.mob instanceof Cat cat) {
            cat.setLying(false);
        }
    }

    public void tick() {
        super.tick();
        this.mob.setInSittingPose(false);
        if(this.mob instanceof Cat cat) {
            if (!this.isReachedTarget()) {
                cat.setLying(false);
            } else if (!cat.isLying()) {
                cat.setLying(true);
            }
        }
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.isEmptyBlock(pPos.above()) && pLevel.getBlockState(pPos).is(BlockTags.BEDS);
    }
}

package com.scouter.goalsmith.data.goals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BegGoalImproved extends Goal {
    private final PathfinderMob mob;
    @Nullable
    private Player player;
    private final Level level;
    private final float lookDistance;
    private int lookTime;
    private final TargetingConditions begTargeting;
    private final TagKey<Item> food;

    public BegGoalImproved(PathfinderMob entity, TagKey<Item> food, float pLookDistance) {
        this.mob = entity;
        this.level = entity.level();
        this.food = food;
        this.lookDistance = pLookDistance;
        this.begTargeting = TargetingConditions.forNonCombat().range((double)pLookDistance);
        this.setFlags(EnumSet.of(Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.begTargeting, this.mob);
        return this.player == null ? false : this.playerHoldingInteresting(this.player);
    }

    @Override
    public boolean canContinueToUse() {
        if (!this.player.isAlive()) {
            return false;
        } else {
            return this.mob.distanceToSqr(this.player) > (double)(this.lookDistance * this.lookDistance)
                    ? false
                    : this.lookTime > 0 && this.playerHoldingInteresting(this.player);
        }
    }

    @Override
    public void start() {
        if(mob instanceof Wolf wolf) {
            wolf.setIsInterested(true);
        }
        this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
    }

    @Override
    public void stop() {
        if(mob instanceof Wolf wolf) {
            wolf.setIsInterested(false);
        }
        this.player = null;
    }

    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.mob.getMaxHeadXRot());
        this.lookTime--;
    }

    /**
     * Gets if the Player has the Bone in the hand.
     */
    private boolean playerHoldingInteresting(Player pPlayer) {
        for (InteractionHand interactionhand : InteractionHand.values()) {
            ItemStack itemstack = pPlayer.getItemInHand(interactionhand);
            if (itemstack.is(food) || this.mob instanceof Animal animal && animal.isFood(itemstack)) {
                return true;
            }
        }

        return false;
    }
}
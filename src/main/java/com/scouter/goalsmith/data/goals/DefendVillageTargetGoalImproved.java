package com.scouter.goalsmith.data.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class DefendVillageTargetGoalImproved extends TargetGoal {
    private final PathfinderMob mob;
    @Nullable
    private LivingEntity potentialTarget;
    private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);

    public DefendVillageTargetGoalImproved(PathfinderMob pGolem) {
        super(pGolem, false, true);
        this.mob = pGolem;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        AABB aabb = this.mob.getBoundingBox().inflate(10.0D, 8.0D, 10.0D);
        List<? extends LivingEntity> list = this.mob.level().getNearbyEntities(Villager.class, this.attackTargeting, this.mob, aabb);
        List<Player> list1 = this.mob.level().getNearbyPlayers(this.attackTargeting, this.mob, aabb);

        for(LivingEntity livingentity : list) {
            Villager villager = (Villager)livingentity;

            for(Player player : list1) {
                int i = villager.getPlayerReputation(player);
                if (i <= -100) {
                    this.potentialTarget = player;
                }
            }
        }

        if (this.potentialTarget == null) {
            return false;
        } else {
            return !(this.potentialTarget instanceof Player) || !this.potentialTarget.isSpectator() && !((Player)this.potentialTarget).isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.mob.setTarget(this.potentialTarget);
        super.start();
    }
}

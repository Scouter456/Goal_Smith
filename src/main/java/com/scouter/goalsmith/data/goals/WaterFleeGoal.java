package com.scouter.goalsmith.data.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class WaterFleeGoal extends Goal {
    private static final float SQUID_FLEE_SPEED = 3.0F;
    private static final float SQUID_FLEE_MIN_DISTANCE = 5.0F;
    private static final float SQUID_FLEE_MAX_DISTANCE = 10.0F;
    private int fleeTicks;
    private PathfinderMob mob;
    
    public WaterFleeGoal(PathfinderMob mob) {
        this.mob = mob;
    }

    public boolean canUse() {
        LivingEntity livingentity = mob.getLastHurtByMob();
        if (mob.isInWater() && livingentity != null) {
            return mob.distanceToSqr(livingentity) < 100.0;
        } else {
            return false;
        }
    }

    public void start() {
        this.fleeTicks = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        ++this.fleeTicks;
        LivingEntity livingentity =mob.getLastHurtByMob();
        if (livingentity != null) {
            Vec3 vec3 = new Vec3(mob.getX() - livingentity.getX(),mob.getY() - livingentity.getY(),mob.getZ() - livingentity.getZ());
            BlockState blockstate =mob.level().getBlockState(BlockPos.containing(mob.getX() + vec3.x,mob.getY() + vec3.y,mob.getZ() + vec3.z));
            FluidState fluidstate =mob.level().getFluidState(BlockPos.containing(mob.getX() + vec3.x,mob.getY() + vec3.y,mob.getZ() + vec3.z));
            if (fluidstate.is(FluidTags.WATER) || blockstate.isAir()) {
                double d0 = vec3.length();
                if (d0 > 0.0) {
                    vec3.normalize();
                    double d1 = 3.0;
                    if (d0 > 5.0) {
                        d1 -= (d0 - 5.0) / 5.0;
                    }

                    if (d1 > 0.0) {
                        vec3 = vec3.scale(d1);
                    }
                }

                if (blockstate.isAir()) {
                    vec3 = vec3.subtract(0.0, vec3.y, 0.0);
                }

                mob.setDeltaMovement((float)vec3.x / 20.0F, (float)vec3.y / 20.0F, (float)vec3.z / 20.0F);
            }

            if (this.fleeTicks % 10 == 5) {
                mob.level().addParticle(ParticleTypes.BUBBLE,mob.getX(),mob.getY(),mob.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }
}
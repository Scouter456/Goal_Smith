package com.scouter.goalsmith.data.goals.entityspecific.wolf;

import com.scouter.goalsmith.data.goals.AvoidEntityGoalImproved;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.Llama;

public class AvoidLlamaGoal extends AvoidEntityGoalImproved {
    private final PathfinderMob mob;
    public AvoidLlamaGoal(PathfinderMob mob, TagKey<EntityType<?>> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
        super(mob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier);
        this.mob = mob;
    }

    public boolean canUse() {
        if (super.canUse() && this.toAvoid instanceof Llama) {
            if(this.mob instanceof TamableAnimal animal) {
                return !animal.isTame() && this.avoidLlama((Llama)this.toAvoid);
            } else {
                return this.avoidLlama((Llama)this.toAvoid);
            }
        } else {
            return false;
        }
    }

    private boolean avoidLlama(Llama pLlama) {
        return pLlama.getStrength() >= mob.level().random.nextInt(5);
    }

    public void start() {
        this.mob.setTarget((LivingEntity)null);
        super.start();
    }

    public void tick() {
        this.mob.setTarget((LivingEntity)null);
        super.tick();
    }
}

package com.scouter.goalsmith.data.goals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class SearchForItemsGoal extends Goal {
    private final PathfinderMob mob;
    private final TagKey<Item> allowedItems;
    public SearchForItemsGoal(PathfinderMob mob, TagKey<Item> allowedItems) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.allowedItems = allowedItems;
    }

    public boolean canUse() {
        if (!mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            return false;
        } else if (mob.getTarget() == null && mob.getLastHurtByMob() == null) {
            if (mob instanceof Fox fox && !fox.canMove()) {
                return false;
            } else if (mob.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                return false;
            } else {
                List<ItemEntity> list = mob.level().getEntitiesOfClass(ItemEntity.class, mob.getBoundingBox().inflate(8.0, 8.0, 8.0), e -> e.getItem().is(allowedItems));
                return !list.isEmpty() && mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        } else {
            return false;
        }
    }

    public void tick() {
        List<ItemEntity> list = mob.level().getEntitiesOfClass(ItemEntity.class, mob.getBoundingBox().inflate(8.0, 8.0, 8.0), e -> e.getItem().is(allowedItems));
        ItemStack itemstack = mob.getItemBySlot(EquipmentSlot.MAINHAND);
        if (itemstack.isEmpty() && !list.isEmpty()) {
            mob.getNavigation().moveTo((Entity)list.getFirst(), 1.2000000476837158);
        }

    }

    public void start() {
        List<ItemEntity> list = mob.level().getEntitiesOfClass(ItemEntity.class, mob.getBoundingBox().inflate(8.0, 8.0, 8.0), e -> e.getItem().is(allowedItems));
        if (!list.isEmpty()) {
            mob.getNavigation().moveTo((Entity)list.get(0), 1.2000000476837158);
        }

    }
}
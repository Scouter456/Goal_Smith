package com.scouter.goalsmith.data.goalcodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scouter.goalsmith.codec.NullableFieldCodec;
import com.scouter.goalsmith.data.GoalCodec;
import com.scouter.goalsmith.data.GoalRegistry;
import com.scouter.goalsmith.data.PredicateCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.item.ItemStack;

public class UseItemGoalCodec implements GoalCodec {
    public static final Codec<UseItemGoalCodec> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("goal_priority").forGetter(UseItemGoalCodec::getGoalPriority),
                    ItemStack.CODEC.fieldOf("item").forGetter(UseItemGoalCodec::getItem),
                    NullableFieldCodec.makeDefaultableField("finish_using_sound", BuiltInRegistries.SOUND_EVENT.byNameCodec(), SoundEvents.WANDERING_TRADER_DRINK_MILK).forGetter(UseItemGoalCodec::getFinishUsingSound),
                    PredicateCodec.DIRECT_CODEC.fieldOf("can_use_predicate").forGetter(UseItemGoalCodec::getCanUseSelector))
            .apply(instance, (integer, itemStack, soundEvent, predicateCodec) ->  new UseItemGoalCodec(integer, itemStack, soundEvent, (PredicateCodec<Entity>) predicateCodec)));

    private final int goalPriority;
    private final ItemStack item;
    private final SoundEvent finishUsingSound;
    private final PredicateCodec<Entity> canUseSelector;

    public UseItemGoalCodec(int goalPriority, ItemStack item, SoundEvent finishUsingSound, PredicateCodec<Entity> canUseSelector) {
        this.goalPriority = goalPriority;
        this.item = item;
        this.finishUsingSound = finishUsingSound;
        this.canUseSelector = canUseSelector;
    }

    public int getGoalPriority() {
        return goalPriority;
    }

    public ItemStack getItem() {
        return item;
    }

    public SoundEvent getFinishUsingSound() {
        return finishUsingSound;
    }

    public PredicateCodec<Entity> getCanUseSelector() {
        return canUseSelector;
    }

    @Override
    public Goal addGoal(PathfinderMob mob) {
        UseItemGoal<Mob> goal = new UseItemGoal<Mob>(mob, item, finishUsingSound, canUseSelector.getPredicate());
        mob.goalSelector.addGoal(goalPriority, goal);
        return goal;
    }

    @Override
    public Codec<? extends GoalCodec> codec() {
        return GoalRegistry.USE_ITEM_GOAL.get();
    }


}
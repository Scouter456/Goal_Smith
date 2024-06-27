package com.scouter.goalsmith.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.*;

public class TargetGoalMappings {
    private static final BiMap<ResourceLocation, Class<? extends TargetGoal>> NAMED_TARGET_GOALS = HashBiMap.create();

    static {
        NAMED_TARGET_GOALS.put(new ResourceLocation( "defend_village_target_goal"), DefendVillageTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "hurt_by_target_goal"), HurtByTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "nearest_attackable_target_goal"), NearestAttackableTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "nearest_attackable_witch_target_goal"), NearestAttackableWitchTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "nearest_attackable_healable_raider_target_goal"), NearestHealableRaiderTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "non_tame_random_target_goal"), NonTameRandomTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "owner_hurt_by_target_goal"), OwnerHurtByTargetGoal.class);
        NAMED_TARGET_GOALS.put(new ResourceLocation( "owner_hurt_target_goal"), OwnerHurtTargetGoal.class);
    }

    public static final Codec<Class<? extends TargetGoal>> CODEC = ExtraCodecs.stringResolverCodec(sa -> NAMED_TARGET_GOALS.inverse().get(sa).toString(), key -> NAMED_TARGET_GOALS.get(new ResourceLocation(key)));


    public static void addTargetGoal(ResourceLocation location, Class<? extends TargetGoal> goal) {
        NAMED_TARGET_GOALS.put(location, goal);
    }

    public static BiMap<ResourceLocation, Class<? extends TargetGoal>> getNamedTargetGoals() {
        return NAMED_TARGET_GOALS;
    }

}

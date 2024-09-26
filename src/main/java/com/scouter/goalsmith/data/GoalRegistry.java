package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.data.goalcodec.*;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RandomStandGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RunAroundLikeCrazyGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.creeper.SwellGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.llama.LlamaFollowCaravanGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.ocelot.OcelotAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.wolf.WolfPanicGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.*;
import net.minecraft.core.Registry;

import static com.scouter.goalsmith.Goalsmith.prefix;

public class GoalRegistry {



    public static final Codec<RandomStrollGoalCodec> RANDOM_STROLL_GOAL = registerGoal("random_stroll_goal", RandomStrollGoalCodec.CODEC);
    public static final Codec<WaterAvoidingRandomStrollGoalCodec> WATER_AVOIDING_RANDOM_STROLL_GOAL = registerGoal("water_avoiding_random_stroll_goal", WaterAvoidingRandomStrollGoalCodec.CODEC);
    public static final Codec<RandomSwimmingGoalCodec> RANDOM_SWIMMING_GOAL = registerGoal("random_swimming_goal", RandomSwimmingGoalCodec.CODEC);
    public static final Codec<MeleeAttackGoalCodec> MELEE_ATTACK_GOAL = registerGoal("melee_attack_goal", MeleeAttackGoalCodec.CODEC);
    public static final Codec<RandomLookAroundGoalCodec> RANDOM_LOOK_AROUND_GOAL = registerGoal("random_look_around_goal", RandomLookAroundGoalCodec.CODEC);
    public static final Codec<RestrictSunGoalCodec> RESTRICT_SUN_GOAL = registerGoal("restrict_sun_goal", RestrictSunGoalCodec.CODEC);
    public static final Codec<FleeSunGoalCodec> FLEE_SUN_GOAL = registerGoal("flee_sun_goal", FleeSunGoalCodec.CODEC);
    public static final Codec<AvoidEntityGoalCodec> AVOID_ENTITY_GOAL = registerGoal("avoid_entity_goal", AvoidEntityGoalCodec.CODEC);
    public static final Codec<AvoidLlamaGoalCodec> AVOID_LLAMA_GOAL = registerGoal("avoid_llama_goal", AvoidLlamaGoalCodec.CODEC);
    public static final Codec<BegGoalCodec> BEG_GOAL = registerGoal("beg_goal", BegGoalCodec.CODEC);
    public static final Codec<BreathAirGoalCodec> BREATH_AIR_GOAL = registerGoal("breath_air_goal", BreathAirGoalCodec.CODEC);
    public static final Codec<BreedGoalCodec> BREED_GOAL = registerGoal("breed_goal", BreedGoalCodec.CODEC);
    public static final Codec<ClimbOnTopOfPowderSnowGoalCodec> CLIMB_ON_TOP_OF_POWDER_SNOW_GOAL = registerGoal("climb_on_top_of_powder_snow_goal", ClimbOnTopOfPowderSnowGoalCodec.CODEC);
    public static final Codec<FollowBoatGoalCodec> FOLLOW_BOAT_GOAL = registerGoal("follow_boat_goal", FollowBoatGoalCodec.CODEC);
    public static final Codec<FollowFlockLeaderGoalCodec> FOLLOW_FLOCK_LEADER_GOAL = registerGoal("follow_flock_leader_goal", FollowFlockLeaderGoalCodec.CODEC);
    public static final Codec<FollowMobGoalCodec> FOLLOW_MOB_GOAL = registerGoal("follow_mob_goal", FollowMobGoalCodec.CODEC);
    public static final Codec<FollowOwnerGoalCodec> FOLLOW_OWNER_GOAL = registerGoal("follow_owner_goal", FollowOwnerGoalCodec.CODEC);
    public static final Codec<FollowParentGoalCodec> FOLLOW_PARENT_GOAL = registerGoal("follow_parent_goal", FollowParentGoalCodec.CODEC);
    public static final Codec<InteractGoalCodec> INTERACT_GOAL = registerGoal("interact_goal", InteractGoalCodec.CODEC);
    public static final Codec<JumpWaterGoalCodec> JUMP_WATER_GOAL = registerGoal("jump_water_goal", JumpWaterGoalCodec.CODEC);
    public static final Codec<LandOnOwnersShoulderGoalCodec> LAND_ON_OWNERS_SHOULDER_GOAL = registerGoal("land_on_owners_shoulders_goal", LandOnOwnersShoulderGoalCodec.CODEC);
    public static final Codec<LeapAtTargetGoalCodec> LEAP_AT_TARGET_GOAL = registerGoal("leap_at_target_goal", LeapAtTargetGoalCodec.CODEC);
    public static final Codec<LieOnBedGoalCodec> LIE_ON_BED_GOAL = registerGoal("lie_on_bed_goal", LieOnBedGoalCodec.CODEC);
    public static final Codec<LookAtEntityGoalCodec> LOOK_AT_ENTITY_GOAL = registerGoal("look_at_entity_goal", LookAtEntityGoalCodec.CODEC);
    public static final Codec<LookAtTradingPlayerGoalCodec> LOOK_AT_TRADING_PLAYER_GOAL = registerGoal("look_at_trading_player_goal", LookAtTradingPlayerGoalCodec.CODEC);
    public static final Codec<MoveBackToVillageGoalCodec> MOVE_BACK_TO_VILLAGE_GOAL = registerGoal("move_back_to_village_goal", MoveBackToVillageGoalCodec.CODEC);
    public static final Codec<MoveThroughVillageGoalCodec> MOVE_THROUGH_VILLAGE_GOAL = registerGoal("move_through_village_goal", MoveThroughVillageGoalCodec.CODEC);
    public static final Codec<MoveToBlockGoalCodec> MOVE_TO_BLOCK_GOAL = registerGoal("move_to_block_goal", MoveToBlockGoalCodec.CODEC);
    public static final Codec<MoveTowardsRestrictionGoalCodec> MOVE_TOWARDS_RESTRICTION_GOAL = registerGoal("move_towards_restriction_goal", MoveTowardsRestrictionGoalCodec.CODEC);
    public static final Codec<MoveTowardsTargetGoalCodec> MOVE_TOWARDS_TARGET_GOAL = registerGoal("move_towards_target_goal", MoveTowardsTargetGoalCodec.CODEC);
    public static final Codec<OfferFlowerGoalCodec> OFFER_FLOWER_GOAL = registerGoal("offer_flower_goal", OfferFlowerGoalCodec.CODEC);
    public static final Codec<OpenDoorGoalCodec> OPEN_DOOR_GOAL = registerGoal("open_door_goal", OpenDoorGoalCodec.CODEC);
    public static final Codec<PathfindToRaidGoalCodec> PATHFIND_TO_RAID_GOAL = registerGoal("pathfind_to_raid_goal", PathfindToRaidGoalCodec.CODEC);
    public static final Codec<RaiseArmAttackGoalCodec> RAISE_ARM_ATTACK_GOAL = registerGoal("raise_arm_attack_goal", RaiseArmAttackGoalCodec.CODEC); //ZombieAttackGoal
    public static final Codec<RandomStrollInVillageGoalCodec> RANDOM_STROLL_IN_VILLAGE_GOAL = registerGoal("random_stroll_in_village_goal", RandomStrollInVillageGoalCodec.CODEC);
    public static final Codec<RangedAttackGoalCodec> RANGED_ATTACK_GOAL = registerGoal("ranged_attack_goal", RangedAttackGoalCodec.CODEC);
    public static final Codec<RangedCrossbowAttackGoalCodec<?>> RANGED_CROSSBOW_ATTACK_GOAL = registerGoal("ranged_crossbow_attack_goal", RangedCrossbowAttackGoalCodec.CODEC);
    public static final Codec<RemoveBlockGoalCodec> REMOVE_BLOCK_GOAL = registerGoal("remove_block_goal", RemoveBlockGoalCodec.CODEC);
    public static final Codec<SearchForItemsGoalCodec> SEARCH_FOR_ITEMS_GOAL = registerGoal("search_for_items_goal", SearchForItemsGoalCodec.CODEC);
    public static final Codec<SitOnBlockGoalCodec> SIT_ON_BLOCK_GOAL = registerGoal("sit_on_block_goal", SitOnBlockGoalCodec.CODEC);
    public static final Codec<SitWhenOrderedToGoalCodec> SIT_WHEN_ORDERED_GOAL = registerGoal("sit_when_ordered_goal", SitWhenOrderedToGoalCodec.CODEC);
    public static final Codec<StrollThroughVillageGoalCodec> STROLL_THROUGH_VILLAGE_GOAL = registerGoal("stroll_through_village_goal", StrollThroughVillageGoalCodec.CODEC);
    public static final Codec<TemptGoalCodec> TEMPT_GOAL = registerGoal("tempt_goal", TemptGoalCodec.CODEC);
    public static final Codec<TradeWithPlayerGoalCodec> TRADE_WITH_PLAYER_GOAL = registerGoal("trade_with_player_goal", TradeWithPlayerGoalCodec.CODEC);
    public static final Codec<TryFindWaterGoalCodec> TRY_FIND_WATER_GOAL = registerGoal("try_find_water_goal", TryFindWaterGoalCodec.CODEC);
    public static final Codec<UseItemGoalCodec> USE_ITEM_GOAL = registerGoal("use_item_goal", UseItemGoalCodec.CODEC);
    public static final Codec<WaterAvoidingRandomFlyingGoalCodec> WATER_AVOIDING_RANDOM_FLYING_GOAL = registerGoal("water_avoiding_random_flying_goal", WaterAvoidingRandomFlyingGoalCodec.CODEC);
    public static final Codec<WaterFleeGoalCodec> WATER_FLEE_GOAL = registerGoal("water_flee_goal", WaterFleeGoalCodec.CODEC);
    public static final Codec<FloatGoalCodec> FLOAT_GOAL = registerGoal("float_goal", FloatGoalCodec.CODEC);
    public static final Codec<RangedBowAttackGoalCodec<?>> RANGED_BOW_ATTACK_GOAL = registerGoal("ranged_bow_attack_goal", RangedBowAttackGoalCodec.CODEC);
    public static final Codec<EatBlockGoalCodec> EAT_BLOCK_GOAL = registerGoal("eat_block_goal", EatBlockGoalCodec.CODEC);
    public static final Codec<BreakDoorGoalCodec> BREAK_DOOR_GOAL = registerGoal("break_door_goal", BreakDoorGoalCodec.CODEC);
    public static final Codec<PanicGoalCodec> PANIC_GOAL = registerGoal("panic_goal", PanicGoalCodec.CODEC);

    //entity specific
    public static final Codec<RandomStandGoalCodec> RANDOM_STAND_GOAL = registerGoal("random_stand_goal", RandomStandGoalCodec.CODEC);
    public static final Codec<RunAroundLikeCrazyGoalCodec> RUN_AROUND_LIKE_CRAZY_GOAL = registerGoal("run_around_like_crazy_goal", RunAroundLikeCrazyGoalCodec.CODEC);
    public static final Codec<SwellGoalCodec> SWELL_GOAL = registerGoal("swell_goal", SwellGoalCodec.CODEC);
    public static final Codec<LlamaFollowCaravanGoalCodec> LLAMA_FOLLOW_CARAVAN_GOAL = registerGoal("llama_follow_caravan_goal", LlamaFollowCaravanGoalCodec.CODEC);
    public static final Codec<OcelotAttackGoalCodec> OCELOT_ATTACK_GOAL = registerGoal("ocelot_attack_goal", OcelotAttackGoalCodec.CODEC);
    public static final Codec<WolfPanicGoalCodec> WOLF_PANIC_GOAL = registerGoal("wolf_panic_goal", WolfPanicGoalCodec.CODEC);



    //target goals
    public static final Codec<NearestAttackableTargetGoalCodec> NEAREST_ATTACKABLE_TARGET_GOAL = registerTargetGoal("nearest_attackable_target_goal", NearestAttackableTargetGoalCodec.CODEC);
    public static final Codec<HurtByTargetGoalCodec> HURT_BY_TARGET_GOAL = registerTargetGoal("hurt_by_target_goal", HurtByTargetGoalCodec.CODEC);
    public static final Codec<DefendVillageTargetGoalCodec> DEFEND_VILLAGE_TARGET_GOAL = registerTargetGoal("defend_village_target_goal", DefendVillageTargetGoalCodec.CODEC);
    public static final Codec<NonTameRandomTargetGoalCodec> NON_TAME_RANDOM_TARGET_GOAL = registerTargetGoal("non_tame_random_target_goal", NonTameRandomTargetGoalCodec.CODEC);
    public static final Codec<OwnerHurtByTargetGoalCodec> OWNER_HURT_BY_TARGET_GOAL = registerTargetGoal("owner_hurt_by_target_goal", OwnerHurtByTargetGoalCodec.CODEC);
    public static final Codec<OwnerHurtTargetGoalCodec> OWNER_HURT_TARGET_GOAL = registerTargetGoal("owner_hurt_target_goal", OwnerHurtTargetGoalCodec.CODEC);

    private static <T extends GoalCodec> Codec<T> registerGoal(String name, Codec<T> type) {
        return Registry.register(GSRegistries.GOAL_TYPE_SERIALIZER, prefix(name), type);
    }

    private static <T extends TargetGoalCodec> Codec<T> registerTargetGoal(String name, Codec<T> type) {
        return Registry.register(GSRegistries.TARGET_GOAL_TYPE_SERIALIZER, prefix(name), type);
    }

    public static void register()
    {
    }

}

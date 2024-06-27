package com.scouter.goalsmith.data;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.goalcodec.*;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RandomStandGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RunAroundLikeCrazyGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.creeper.SwellGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.llama.LlamaFollowCaravanGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.ocelot.OcelotAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.wolf.WolfPanicGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoalRegistry {
    public static final DeferredRegister<MapCodec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = DeferredRegister.create(PMRegistries.Keys.GOAL_TYPE_SERIALIZERS, GoalSmith.MODID);
    public static final DeferredRegister<MapCodec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = DeferredRegister.create(PMRegistries.Keys.TARGET_GOAL_TYPE_SERIALIZERS, GoalSmith.MODID);

    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RandomStrollGoalCodec>> RANDOM_STROLL_GOAL = GOAL_TYPE_SERIALIZER.register("random_stroll_goal", () -> RandomStrollGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<WaterAvoidingRandomStrollGoalCodec>> WATER_AVOIDING_RANDOM_STROLL_GOAL = GOAL_TYPE_SERIALIZER.register("water_avoiding_random_stroll_goal", () -> WaterAvoidingRandomStrollGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RandomSwimmingGoalCodec>> RANDOM_SWIMMING_GOAL = GOAL_TYPE_SERIALIZER.register("random_swimming_goal", () -> RandomSwimmingGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MeleeAttackGoalCodec>> MELEE_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("melee_attack_goal", () -> MeleeAttackGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RandomLookAroundGoalCodec>> RANDOM_LOOK_AROUND_GOAL = GOAL_TYPE_SERIALIZER.register("random_look_around_goal", () -> RandomLookAroundGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RestrictSunGoalCodec>> RESTRICT_SUN_GOAL = GOAL_TYPE_SERIALIZER.register("restrict_sun_goal", () -> RestrictSunGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FleeSunGoalCodec>> FLEE_SUN_GOAL = GOAL_TYPE_SERIALIZER.register("flee_sun_goal", () -> FleeSunGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<AvoidEntityGoalCodec>> AVOID_ENTITY_GOAL = GOAL_TYPE_SERIALIZER.register("avoid_entity_goal", () -> AvoidEntityGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<AvoidLlamaGoalCodec>> AVOID_LLAMA_GOAL = GOAL_TYPE_SERIALIZER.register("avoid_llama_goal", () -> AvoidLlamaGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<BegGoalCodec>> BEG_GOAL = GOAL_TYPE_SERIALIZER.register("beg_goal", () -> BegGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<BreathAirGoalCodec>> BREATH_AIR_GOAL = GOAL_TYPE_SERIALIZER.register("breath_air_goal", () -> BreathAirGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<BreedGoalCodec>> BREED_GOAL = GOAL_TYPE_SERIALIZER.register("breed_goal", () -> BreedGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<ClimbOnTopOfPowderSnowGoalCodec>> CLIMB_ON_TOP_OF_POWDER_SNOW_GOAL = GOAL_TYPE_SERIALIZER.register("climb_on_top_of_powder_snow_goal", () -> ClimbOnTopOfPowderSnowGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FollowBoatGoalCodec>> FOLLOW_BOAT_GOAL = GOAL_TYPE_SERIALIZER.register("follow_boat_goal", () -> FollowBoatGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FollowFlockLeaderGoalCodec>> FOLLOW_FLOCK_LEADER_GOAL = GOAL_TYPE_SERIALIZER.register("follow_flock_leader_goal", () -> FollowFlockLeaderGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FollowMobGoalCodec>> FOLLOW_MOB_GOAL = GOAL_TYPE_SERIALIZER.register("follow_mob_goal", () -> FollowMobGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FollowOwnerGoalCodec>> FOLLOW_OWNER_GOAL = GOAL_TYPE_SERIALIZER.register("follow_owner_goal", () -> FollowOwnerGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FollowParentGoalCodec>> FOLLOW_PARENT_GOAL = GOAL_TYPE_SERIALIZER.register("follow_parent_goal", () -> FollowParentGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<InteractGoalCodec>> INTERACT_GOAL = GOAL_TYPE_SERIALIZER.register("interact_goal", () -> InteractGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<JumpWaterGoalCodec>> JUMP_WATER_GOAL = GOAL_TYPE_SERIALIZER.register("jump_water_goal", () -> JumpWaterGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LandOnOwnersShoulderGoalCodec>> LAND_ON_OWNERS_SHOULDER_GOAL = GOAL_TYPE_SERIALIZER.register("land_on_owners_shoulders_goal", () -> LandOnOwnersShoulderGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LeapAtTargetGoalCodec>> LEAP_AT_TARGET_GOAL = GOAL_TYPE_SERIALIZER.register("leap_at_target_goal", () -> LeapAtTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LieOnBedGoalCodec>> LIE_ON_BED_GOAL = GOAL_TYPE_SERIALIZER.register("lie_on_bed_goal", () -> LieOnBedGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LookAtEntityGoalCodec>> LOOK_AT_ENTITY_GOAL = GOAL_TYPE_SERIALIZER.register("look_at_entity_goal", () -> LookAtEntityGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LookAtTradingPlayerGoalCodec>> LOOK_AT_TRADING_PLAYER_GOAL = GOAL_TYPE_SERIALIZER.register("look_at_trading_player_goal", () -> LookAtTradingPlayerGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MoveBackToVillageGoalCodec>> MOVE_BACK_TO_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("move_back_to_village_goal", () -> MoveBackToVillageGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MoveThroughVillageGoalCodec>> MOVE_THROUGH_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("move_through_village_goal", () -> MoveThroughVillageGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MoveToBlockGoalCodec>> MOVE_TO_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("move_to_block_goal", () -> MoveToBlockGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MoveTowardsRestrictionGoalCodec>> MOVE_TOWARDS_RESTRICTION_GOAL = GOAL_TYPE_SERIALIZER.register("move_towards_restriction_goal", () -> MoveTowardsRestrictionGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<MoveTowardsTargetGoalCodec>> MOVE_TOWARDS_TARGET_GOAL = GOAL_TYPE_SERIALIZER.register("move_towards_target_goal", () -> MoveTowardsTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<OfferFlowerGoalCodec>> OFFER_FLOWER_GOAL = GOAL_TYPE_SERIALIZER.register("offer_flower_goal", () -> OfferFlowerGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<OpenDoorGoalCodec>> OPEN_DOOR_GOAL = GOAL_TYPE_SERIALIZER.register("open_door_goal", () -> OpenDoorGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<PathfindToRaidGoalCodec>> PATHFIND_TO_RAID_GOAL = GOAL_TYPE_SERIALIZER.register("pathfind_to_raid_goal", () -> PathfindToRaidGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RaiseArmAttackGoalCodec>> RAISE_ARM_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("raise_arm_attack_goal", () -> RaiseArmAttackGoalCodec.CODEC); //ZombieAttackGoal
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RandomStrollInVillageGoalCodec>> RANDOM_STROLL_IN_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("random_stroll_in_village_goal", () -> RandomStrollInVillageGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RangedAttackGoalCodec>> RANGED_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_attack_goal", () -> RangedAttackGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RangedCrossbowAttackGoalCodec<?>>> RANGED_CROSSBOW_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_crossbow_attack_goal", () -> RangedCrossbowAttackGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RemoveBlockGoalCodec>> REMOVE_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("remove_block_goal", () -> RemoveBlockGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<SearchForItemsGoalCodec>> SEARCH_FOR_ITEMS_GOAL = GOAL_TYPE_SERIALIZER.register("search_for_items_goal", () -> SearchForItemsGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<SitOnBlockGoalCodec>> SIT_ON_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("sit_on_block_goal", () -> SitOnBlockGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<SitWhenOrderedToGoalCodec>> SIT_WHEN_ORDERED_GOAL = GOAL_TYPE_SERIALIZER.register("sit_when_ordered_goal", () -> SitWhenOrderedToGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<StrollThroughVillageGoalCodec>> STROLL_THROUGH_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("stroll_through_village_goal", () -> StrollThroughVillageGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<TemptGoalCodec>> TEMPT_GOAL = GOAL_TYPE_SERIALIZER.register("tempt_goal", () -> TemptGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<TradeWithPlayerGoalCodec>> TRADE_WITH_PLAYER_GOAL = GOAL_TYPE_SERIALIZER.register("trade_with_player_goal", () -> TradeWithPlayerGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<TryFindWaterGoalCodec>> TRY_FIND_WATER_GOAL = GOAL_TYPE_SERIALIZER.register("try_find_water_goal", () -> TryFindWaterGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<UseItemGoalCodec>> USE_ITEM_GOAL = GOAL_TYPE_SERIALIZER.register("use_item_goal", () -> UseItemGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<WaterAvoidingRandomFlyingGoalCodec>> WATER_AVOIDING_RANDOM_FLYING_GOAL = GOAL_TYPE_SERIALIZER.register("water_avoiding_random_flying_goal", () -> WaterAvoidingRandomFlyingGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<WaterFleeGoalCodec>> WATER_FLEE_GOAL = GOAL_TYPE_SERIALIZER.register("water_flee_goal", () -> WaterFleeGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<FloatGoalCodec>> FLOAT_GOAL = GOAL_TYPE_SERIALIZER.register("float_goal", () -> FloatGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RangedBowAttackGoalCodec<?>>> RANGED_BOW_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_bow_attack_goal", () -> RangedBowAttackGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<EatBlockGoalCodec>> EAT_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("eat_block_goal", () -> EatBlockGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<BreakDoorGoalCodec>> BREAK_DOOR_GOAL = GOAL_TYPE_SERIALIZER.register("break_door_goal", () -> BreakDoorGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<PanicGoalCodec>> PANIC_GOAL = GOAL_TYPE_SERIALIZER.register("panic_goal", () -> PanicGoalCodec.CODEC);

    //entity specific
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RandomStandGoalCodec>> RANDOM_STAND_GOAL = GOAL_TYPE_SERIALIZER.register("random_stand_goal", () -> RandomStandGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<RunAroundLikeCrazyGoalCodec>> RUN_AROUND_LIKE_CRAZY_GOAL = GOAL_TYPE_SERIALIZER.register("run_around_like_crazy_goal", () -> RunAroundLikeCrazyGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<SwellGoalCodec>> SWELL_GOAL = GOAL_TYPE_SERIALIZER.register("swell_goal", () -> SwellGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<LlamaFollowCaravanGoalCodec>> LLAMA_FOLLOW_CARAVAN_GOAL = GOAL_TYPE_SERIALIZER.register("llama_follow_caravan_goal", () -> LlamaFollowCaravanGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<OcelotAttackGoalCodec>> OCELOT_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ocelot_attack_goal", () -> OcelotAttackGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends GoalCodec>, MapCodec<WolfPanicGoalCodec>> WOLF_PANIC_GOAL = GOAL_TYPE_SERIALIZER.register("wolf_panic_goal", () -> WolfPanicGoalCodec.CODEC);



    //target goals
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<NearestAttackableTargetGoalCodec>> NEAREST_ATTACKABLE_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("nearest_attackable_target_goal", () -> NearestAttackableTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<HurtByTargetGoalCodec>> HURT_BY_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("hurt_by_target_goal", () -> HurtByTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<DefendVillageTargetGoalCodec>> DEFEND_VILLAGE_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("defend_village_target_goal", () -> DefendVillageTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<NonTameRandomTargetGoalCodec>> NON_TAME_RANDOM_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("non_tame_random_target_goal", () -> NonTameRandomTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<OwnerHurtByTargetGoalCodec>> OWNER_HURT_BY_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("owner_hurt_by_target_goal", () -> OwnerHurtByTargetGoalCodec.CODEC);
    public static final DeferredHolder<MapCodec<? extends TargetGoalCodec>, MapCodec<OwnerHurtTargetGoalCodec>> OWNER_HURT_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("owner_hurt_target_goal", () -> OwnerHurtTargetGoalCodec.CODEC);


}

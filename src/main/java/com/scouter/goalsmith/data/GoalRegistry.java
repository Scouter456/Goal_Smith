package com.scouter.goalsmith.data;

import com.mojang.serialization.Codec;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.goalcodec.*;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RandomStandGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.abstracthorse.RunAroundLikeCrazyGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.creeper.SwellGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.llama.LlamaFollowCaravanGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.ocelot.OcelotAttackGoalCodec;
import com.scouter.goalsmith.data.goalcodec.entityspecific.wolf.WolfPanicGoalCodec;
import com.scouter.goalsmith.data.goalcodec.targetgoalcodec.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GoalRegistry {
    public static final DeferredRegister<Codec<? extends GoalCodec>> GOAL_TYPE_SERIALIZER = DeferredRegister.create(PMRegistries.Keys.GOAL_TYPE_SERIALIZERS, GoalSmith.MODID);
    public static final DeferredRegister<Codec<? extends TargetGoalCodec>> TARGET_GOAL_TYPE_SERIALIZER = DeferredRegister.create(PMRegistries.Keys.TARGET_GOAL_TYPE_SERIALIZERS, GoalSmith.MODID);


    public static final RegistryObject<Codec<RandomStrollGoalCodec>> RANDOM_STROLL_GOAL = GOAL_TYPE_SERIALIZER.register("random_stroll_goal", () -> RandomStrollGoalCodec.CODEC);
    public static final RegistryObject<Codec<WaterAvoidingRandomStrollGoalCodec>> WATER_AVOIDING_RANDOM_STROLL_GOAL = GOAL_TYPE_SERIALIZER.register("water_avoiding_random_stroll_goal", () -> WaterAvoidingRandomStrollGoalCodec.CODEC);
    public static final RegistryObject<Codec<RandomSwimmingGoalCodec>> RANDOM_SWIMMING_GOAL = GOAL_TYPE_SERIALIZER.register("random_swimming_goal", () -> RandomSwimmingGoalCodec.CODEC);
    public static final RegistryObject<Codec<MeleeAttackGoalCodec>> MELEE_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("melee_attack_goal", () -> MeleeAttackGoalCodec.CODEC);
    public static final RegistryObject<Codec<RandomLookAroundGoalCodec>> RANDOM_LOOK_AROUND_GOAL = GOAL_TYPE_SERIALIZER.register("random_look_around_goal", () -> RandomLookAroundGoalCodec.CODEC);
    public static final RegistryObject<Codec<RestrictSunGoalCodec>> RESTRICT_SUN_GOAL = GOAL_TYPE_SERIALIZER.register("restrict_sun_goal", () -> RestrictSunGoalCodec.CODEC);
    public static final RegistryObject<Codec<FleeSunGoalCodec>> FLEE_SUN_GOAL = GOAL_TYPE_SERIALIZER.register("flee_sun_goal", () -> FleeSunGoalCodec.CODEC);
    public static final RegistryObject<Codec<AvoidEntityGoalCodec>> AVOID_ENTITY_GOAL = GOAL_TYPE_SERIALIZER.register("avoid_entity_goal", () -> AvoidEntityGoalCodec.CODEC);
    public static final RegistryObject<Codec<AvoidLlamaGoalCodec>> AVOID_LLAMA_GOAL = GOAL_TYPE_SERIALIZER.register("avoid_llama_goal", () -> AvoidLlamaGoalCodec.CODEC);
    public static final RegistryObject<Codec<BegCoalCodec>> BEG_GOAL = GOAL_TYPE_SERIALIZER.register("beg_goal", () -> BegCoalCodec.CODEC);
    public static final RegistryObject<Codec<BreathAirGoalCodec>> BREATH_AIR_GOAL = GOAL_TYPE_SERIALIZER.register("breath_air_goal", () -> BreathAirGoalCodec.CODEC);
    public static final RegistryObject<Codec<BreedGoalCodec>> BREED_GOAL = GOAL_TYPE_SERIALIZER.register("breed_goal", () -> BreedGoalCodec.CODEC);
    public static final RegistryObject<Codec<ClimbOnTopOfPowderSnowGoalCodec>> CLIMB_ON_TOP_OF_POWDER_SNOW_GOAL = GOAL_TYPE_SERIALIZER.register("climb_on_top_of_powder_snow_goal", () -> ClimbOnTopOfPowderSnowGoalCodec.CODEC);
    public static final RegistryObject<Codec<FollowBoatGoalCodec>> FOLLOW_BOAT_GOAL = GOAL_TYPE_SERIALIZER.register("follow_boat_goal", () -> FollowBoatGoalCodec.CODEC);
    public static final RegistryObject<Codec<FollowFlockLeaderGoalCodec>> FOLLOW_FLOCK_LEADER_GOAL = GOAL_TYPE_SERIALIZER.register("follow_flock_leader_goal", () -> FollowFlockLeaderGoalCodec.CODEC);
    public static final RegistryObject<Codec<FollowMobGoalCodec>> FOLLOW_MOB_GOAL = GOAL_TYPE_SERIALIZER.register("follow_mob_goal", () -> FollowMobGoalCodec.CODEC);
    public static final RegistryObject<Codec<FollowOwnerGoalCodec>> FOLLOW_OWNER_GOAL = GOAL_TYPE_SERIALIZER.register("follow_owner_goal", () -> FollowOwnerGoalCodec.CODEC);
    public static final RegistryObject<Codec<FollowParentGoalCodec>> FOLLOW_PARENT_GOAL = GOAL_TYPE_SERIALIZER.register("follow_parent_goal", () -> FollowParentGoalCodec.CODEC);
    public static final RegistryObject<Codec<InteractGoalCodec>> INTERACT_GOAL = GOAL_TYPE_SERIALIZER.register("interact_goal", () -> InteractGoalCodec.CODEC);
    public static final RegistryObject<Codec<JumpWaterGoalCodec>> JUMP_WATER_GOAL = GOAL_TYPE_SERIALIZER.register("jump_water_goal", () -> JumpWaterGoalCodec.CODEC);
    public static final RegistryObject<Codec<LandOnOwnersShoulderGoalCodec>> LAND_ON_OWNERS_SHOULDER_GOAL = GOAL_TYPE_SERIALIZER.register("land_on_owners_shoulders_goal", () -> LandOnOwnersShoulderGoalCodec.CODEC);
    public static final RegistryObject<Codec<LeapAtTargetGoalCodec>> LEAP_AT_TARGET_GOAL = GOAL_TYPE_SERIALIZER.register("leap_at_target_goal", () -> LeapAtTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<LieOnBedGoalCodec>> LIE_ON_BED_GOAL = GOAL_TYPE_SERIALIZER.register("lie_on_bed_goal", () -> LieOnBedGoalCodec.CODEC);
    public static final RegistryObject<Codec<LookAtEntityGoalCodec>> LOOK_AT_ENTITY_GOAL = GOAL_TYPE_SERIALIZER.register("look_at_entity_goal", () -> LookAtEntityGoalCodec.CODEC);
    public static final RegistryObject<Codec<LookAtTradingPlayerGoalCodec>> LOOK_AT_TRADING_PLAYER_GOAL = GOAL_TYPE_SERIALIZER.register("look_at_trading_player_goal", () -> LookAtTradingPlayerGoalCodec.CODEC);
    public static final RegistryObject<Codec<MoveBackToVillageGoalCodec>> MOVE_BACK_TO_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("move_back_to_village_goal", () -> MoveBackToVillageGoalCodec.CODEC);
    public static final RegistryObject<Codec<MoveThroughVillageGoalCodec>> MOVE_THROUGH_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("move_through_village_goal", () -> MoveThroughVillageGoalCodec.CODEC);
    public static final RegistryObject<Codec<MoveToBlockGoalCodec>> MOVE_TO_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("move_to_block_goal", () -> MoveToBlockGoalCodec.CODEC);
    public static final RegistryObject<Codec<MoveTowardsRestrictionGoalCodec>> MOVE_TOWARDS_RESTRICTION_GOAL = GOAL_TYPE_SERIALIZER.register("move_towards_restriction_goal", () -> MoveTowardsRestrictionGoalCodec.CODEC);
    public static final RegistryObject<Codec<MoveTowardsTargetGoalCodec>> MOVE_TOWARDS_TARGET_GOAL = GOAL_TYPE_SERIALIZER.register("move_towards_target_goal", () -> MoveTowardsTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<OfferFlowerGoalCodec>> OFFER_FLOWER_GOAL = GOAL_TYPE_SERIALIZER.register("offer_flower_goal", () -> OfferFlowerGoalCodec.CODEC);
    public static final RegistryObject<Codec<OpenDoorGoalCodec>> OPEN_DOOR_GOAL = GOAL_TYPE_SERIALIZER.register("open_door_goal", () -> OpenDoorGoalCodec.CODEC);
    public static final RegistryObject<Codec<PathfindToRaidGoalCodec>> PATHFIND_TO_RAID_GOAL = GOAL_TYPE_SERIALIZER.register("pathfind_to_raid_goal", () -> PathfindToRaidGoalCodec.CODEC);
    public static final RegistryObject<Codec<RaiseArmAttackGoalCodec>> RAISE_ARM_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("raise_arm_attack_goal", () -> RaiseArmAttackGoalCodec.CODEC); //ZombieAttackGoal
    public static final RegistryObject<Codec<RandomStrollInVillageGoalCodec>> RANDOM_STROLL_IN_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("random_stroll_in_village_goal", () -> RandomStrollInVillageGoalCodec.CODEC);
    public static final RegistryObject<Codec<RangedAttackGoalCodec>> RANGED_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_attack_goal", () -> RangedAttackGoalCodec.CODEC);
    public static final RegistryObject<Codec<RangedCrossbowAttackGoalCodec<?>>> RANGED_CROSSBOW_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_crossbow_attack_goal", () -> RangedCrossbowAttackGoalCodec.CODEC);
    public static final RegistryObject<Codec<RemoveBlockGoalCodec>> REMOVE_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("remove_block_goal", () -> RemoveBlockGoalCodec.CODEC);
    public static final RegistryObject<Codec<SearchForItemsGoalCodec>> SEARCH_FOR_ITEMS_GOAL = GOAL_TYPE_SERIALIZER.register("search_for_items_goal", () -> SearchForItemsGoalCodec.CODEC);
    public static final RegistryObject<Codec<SitOnBlockGoalCodec>> SIT_ON_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("sit_on_block_goal", () -> SitOnBlockGoalCodec.CODEC);
    public static final RegistryObject<Codec<SitWhenOrderedToGoalCodec>> SIT_WHEN_ORDERED_GOAL = GOAL_TYPE_SERIALIZER.register("sit_when_ordered_goal", () -> SitWhenOrderedToGoalCodec.CODEC);
    public static final RegistryObject<Codec<StrollThroughVillageGoalCodec>> STROLL_THROUGH_VILLAGE_GOAL = GOAL_TYPE_SERIALIZER.register("stroll_through_village_goal", () -> StrollThroughVillageGoalCodec.CODEC);
    public static final RegistryObject<Codec<TemptGoalCodec>> TEMPT_GOAL = GOAL_TYPE_SERIALIZER.register("tempt_goal", () -> TemptGoalCodec.CODEC);
    public static final RegistryObject<Codec<TradeWithPlayerGoalCodec>> TRADE_WITH_PLAYER_GOAL = GOAL_TYPE_SERIALIZER.register("trade_with_player_goal", () -> TradeWithPlayerGoalCodec.CODEC);
    public static final RegistryObject<Codec<TryFindWaterGoalCodec>> TRY_FIND_WATER_GOAL = GOAL_TYPE_SERIALIZER.register("try_find_water_goal", () -> TryFindWaterGoalCodec.CODEC);
    public static final RegistryObject<Codec<UseItemGoalCodec>> USE_ITEM_GOAL = GOAL_TYPE_SERIALIZER.register("use_item_goal", () -> UseItemGoalCodec.CODEC);
    public static final RegistryObject<Codec<WaterAvoidingRandomFlyingGoalCodec>> WATER_AVOIDING_RANDOM_FLYING_GOAL = GOAL_TYPE_SERIALIZER.register("water_avoiding_random_flying_goal", () -> WaterAvoidingRandomFlyingGoalCodec.CODEC);
    public static final RegistryObject<Codec<WaterFleeGoalCodec>> WATER_FLEE_GOAL = GOAL_TYPE_SERIALIZER.register("water_flee_goal", () -> WaterFleeGoalCodec.CODEC);
    public static final RegistryObject<Codec<FloatGoalCodec>> FLOAT_GOAL = GOAL_TYPE_SERIALIZER.register("float_goal", () -> FloatGoalCodec.CODEC);
    public static final RegistryObject<Codec<RangedBowAttackGoalCodec<?>>> RANGED_BOW_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ranged_bow_attack_goal", () -> RangedBowAttackGoalCodec.CODEC);
    public static final RegistryObject<Codec<EatBlockGoalCodec>> EAT_BLOCK_GOAL = GOAL_TYPE_SERIALIZER.register("eat_block_goal", () -> EatBlockGoalCodec.CODEC);
    public static final RegistryObject<Codec<BreakDoorGoalCodec>> BREAK_DOOR_GOAL = GOAL_TYPE_SERIALIZER.register("break_door_goal", () -> BreakDoorGoalCodec.CODEC);
    public static final RegistryObject<Codec<PanicGoalCodec>> PANIC_GOAL = GOAL_TYPE_SERIALIZER.register("panic_goal", () -> PanicGoalCodec.CODEC);

    //entity specific
    public static final RegistryObject<Codec<RandomStandGoalCodec>> RANDOM_STAND_GOAL = GOAL_TYPE_SERIALIZER.register("random_stand_goal", () -> RandomStandGoalCodec.CODEC);
    public static final RegistryObject<Codec<RunAroundLikeCrazyGoalCodec>> RUN_AROUND_LIKE_CRAZY_GOAL = GOAL_TYPE_SERIALIZER.register("run_around_like_crazy_goal", () -> RunAroundLikeCrazyGoalCodec.CODEC);
    public static final RegistryObject<Codec<SwellGoalCodec>> SWELL_GOAL = GOAL_TYPE_SERIALIZER.register("swell_goal", () -> SwellGoalCodec.CODEC);
    public static final RegistryObject<Codec<LlamaFollowCaravanGoalCodec>> LLAMA_FOLLOW_CARAVAN_GOAL = GOAL_TYPE_SERIALIZER.register("llama_follow_caravan_goal", () -> LlamaFollowCaravanGoalCodec.CODEC);
    public static final RegistryObject<Codec<OcelotAttackGoalCodec>> OCELOT_ATTACK_GOAL = GOAL_TYPE_SERIALIZER.register("ocelot_attack_goal", () -> OcelotAttackGoalCodec.CODEC);
    public static final RegistryObject<Codec<WolfPanicGoalCodec>> WOLF_PANIC_GOAL = GOAL_TYPE_SERIALIZER.register("wolf_panic_goal", () -> WolfPanicGoalCodec.CODEC);



    //target goals
    public static final RegistryObject<Codec<NearestAttackableTargetGoalCodec>> NEAREST_ATTACKABLE_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("nearest_attackable_target_goal", () -> NearestAttackableTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<HurtByTargetGoalCodec>> HURT_BY_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("hurt_by_target_goal", () -> HurtByTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<DefendVillageTargetGoalCodec>> DEFEND_VILLAGE_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("defend_village_target_goal", () -> DefendVillageTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<NonTameRandomTargetGoalCodec>> NON_TAME_RANDOM_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("non_tame_random_target_goal", () -> NonTameRandomTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<OwnerHurtByTargetGoalCodec>> OWNER_HURT_BY_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("owner_hurt_by_target_goal", () -> OwnerHurtByTargetGoalCodec.CODEC);
    public static final RegistryObject<Codec<OwnerHurtTargetGoalCodec>> OWNER_HURT_TARGET_GOAL = TARGET_GOAL_TYPE_SERIALIZER.register("owner_hurt_target_goal", () -> OwnerHurtTargetGoalCodec.CODEC);


}

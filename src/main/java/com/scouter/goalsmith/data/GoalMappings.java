package com.scouter.goalsmith.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.*;

public class GoalMappings {
    private static final BiMap<ResourceLocation, Class<? extends Goal>> NAMED_GOALS = HashBiMap.create();

    static {
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "avoid_entity_goal"), AvoidEntityGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "beg_goal"), BegGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "break_door_goal"), BreakDoorGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "breath_air_goal"), BreathAirGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "breed_goal"), BreedGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "cat_lie_on_bed_goal"), CatLieOnBedGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "cat_sit_on_block_goal"), CatSitOnBlockGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "climb_on_top_of_powder_snow_goal"), ClimbOnTopOfPowderSnowGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "dolphin_jump_goal"), DolphinJumpGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "door_interact_goal"), DoorInteractGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "eat_block_goal"), EatBlockGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "flee_sun_goal"), FleeSunGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "float_goal"), FloatGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "follow_boat_goal"), FollowBoatGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "follow_flock_leader_goal"), FollowFlockLeaderGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "follow_mob_goal"), FollowMobGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "follow_owner_goal"), FollowOwnerGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "follow_parent_goal"), FollowParentGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "goal_goal"), Goal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "golem_random_stroll_in_village_goal"), GolemRandomStrollInVillageGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "interact_goal"), InteractGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "jump_goal"), JumpGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "land_on_owners_shoulder_goal"), LandOnOwnersShoulderGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "leap_at_target_goal"), LeapAtTargetGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "llama_follow_caravan_goal"), LlamaFollowCaravanGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "look_at_player_goal"), LookAtPlayerGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "look_at_trading_player_goal"), LookAtTradingPlayerGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "melee_attack_goal"), MeleeAttackGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "move_back_to_village_goal"), MoveBackToVillageGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "move_through_village_goal"), MoveThroughVillageGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "move_to_block_goal"), MoveToBlockGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "move_towards_restriction_goal"), MoveTowardsRestrictionGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "move_towards_target_goal"), MoveTowardsTargetGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "ocelot_attack_goal"), OcelotAttackGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "offer_flower_goal"), OfferFlowerGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "open_door_goal"), OpenDoorGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "panic_goal"), PanicGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "pathfind_to_raid_goal"), PathfindToRaidGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "random_look_around_goal"), RandomLookAroundGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "random_stand_goal"), RandomStandGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "random_stroll_goal"), RandomStrollGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "random_swimming_goal"), RandomSwimmingGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "ranged_attack_goal"), RangedAttackGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "ranged_bow_attack_goal"), RangedBowAttackGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "ranged_crossbow_attack_goal"), RangedCrossbowAttackGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "remove_block_goal"), RemoveBlockGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "restrict_sun_goal"), RestrictSunGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "run_around_like_crazy_goal"), RunAroundLikeCrazyGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "sit_when_ordered_to_goal"), SitWhenOrderedToGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "stroll_through_village_goal"), StrollThroughVillageGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "swell_goal"), SwellGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "tempt_goal"), TemptGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "trade_with_player_goal"), TradeWithPlayerGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "try_find_water_goal"), TryFindWaterGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "use_item_goal"), UseItemGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "water_avoiding_random_flying_goal"), WaterAvoidingRandomFlyingGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "water_avoiding_random_stroll_goal"), WaterAvoidingRandomStrollGoal.class);
        NAMED_GOALS.put(ResourceLocation.withDefaultNamespace( "zombie_attack_goal"), ZombieAttackGoal.class);
    }

    public static final Codec<Class<? extends Goal>> CODEC = Codec.stringResolver(sa -> NAMED_GOALS.inverse().get(sa).toString(), key -> NAMED_GOALS.get(ResourceLocation.tryParse(key)));

    public static void addGoal(ResourceLocation location, Class<? extends Goal> goal) {
        NAMED_GOALS.put(location, goal);
    }

    public static BiMap<ResourceLocation, Class<? extends Goal>> getNamedGoals() {
        return NAMED_GOALS;
    }
}

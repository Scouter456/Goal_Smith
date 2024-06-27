package com.scouter.goalsmith.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;

public class GoalMappings {
    private static final BiMap<ResourceLocation, Class<? extends Goal>> NAMED_GOALS = HashBiMap.create();

    static {
        NAMED_GOALS.put(new ResourceLocation( "avoid_entity_goal"), AvoidEntityGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "beg_goal"), BegGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "break_door_goal"), BreakDoorGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "breath_air_goal"), BreathAirGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "breed_goal"), BreedGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "cat_lie_on_bed_goal"), CatLieOnBedGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "cat_sit_on_block_goal"), CatSitOnBlockGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "climb_on_top_of_powder_snow_goal"), ClimbOnTopOfPowderSnowGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "dolphin_jump_goal"), DolphinJumpGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "door_interact_goal"), DoorInteractGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "eat_block_goal"), EatBlockGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "flee_sun_goal"), FleeSunGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "float_goal"), FloatGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "follow_boat_goal"), FollowBoatGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "follow_flock_leader_goal"), FollowFlockLeaderGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "follow_mob_goal"), FollowMobGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "follow_owner_goal"), FollowOwnerGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "follow_parent_goal"), FollowParentGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "goal_goal"), Goal.class);
        NAMED_GOALS.put(new ResourceLocation( "golem_random_stroll_in_village_goal"), GolemRandomStrollInVillageGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "interact_goal"), InteractGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "jump_goal"), JumpGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "land_on_owners_shoulder_goal"), LandOnOwnersShoulderGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "leap_at_target_goal"), LeapAtTargetGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "llama_follow_caravan_goal"), LlamaFollowCaravanGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "look_at_player_goal"), LookAtPlayerGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "look_at_trading_player_goal"), LookAtTradingPlayerGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "melee_attack_goal"), MeleeAttackGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "move_back_to_village_goal"), MoveBackToVillageGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "move_through_village_goal"), MoveThroughVillageGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "move_to_block_goal"), MoveToBlockGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "move_towards_restriction_goal"), MoveTowardsRestrictionGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "move_towards_target_goal"), MoveTowardsTargetGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "ocelot_attack_goal"), OcelotAttackGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "offer_flower_goal"), OfferFlowerGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "open_door_goal"), OpenDoorGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "panic_goal"), PanicGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "pathfind_to_raid_goal"), PathfindToRaidGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "random_look_around_goal"), RandomLookAroundGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "random_stand_goal"), RandomStandGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "random_stroll_goal"), RandomStrollGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "random_swimming_goal"), RandomSwimmingGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "ranged_attack_goal"), RangedAttackGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "ranged_bow_attack_goal"), RangedBowAttackGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "ranged_crossbow_attack_goal"), RangedCrossbowAttackGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "remove_block_goal"), RemoveBlockGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "restrict_sun_goal"), RestrictSunGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "run_around_like_crazy_goal"), RunAroundLikeCrazyGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "sit_when_ordered_to_goal"), SitWhenOrderedToGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "stroll_through_village_goal"), StrollThroughVillageGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "swell_goal"), SwellGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "tempt_goal"), TemptGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "trade_with_player_goal"), TradeWithPlayerGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "try_find_water_goal"), TryFindWaterGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "use_item_goal"), UseItemGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "water_avoiding_random_flying_goal"), WaterAvoidingRandomFlyingGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "water_avoiding_random_stroll_goal"), WaterAvoidingRandomStrollGoal.class);
        NAMED_GOALS.put(new ResourceLocation( "zombie_attack_goal"), ZombieAttackGoal.class);
    }

    public static final Codec<Class<? extends Goal>> CODEC = ExtraCodecs.stringResolverCodec(sa -> NAMED_GOALS.inverse().get(sa).toString(), key -> NAMED_GOALS.get(new ResourceLocation(key)));

    public static void addGoal(ResourceLocation location, Class<? extends Goal> goal) {
        NAMED_GOALS.put(location, goal);
    }

    public static BiMap<ResourceLocation, Class<? extends Goal>> getNamedGoals() {
        return NAMED_GOALS;
    }
}

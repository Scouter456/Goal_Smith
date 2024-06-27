package com.scouter.goalsmith.data;

import com.mojang.serialization.MapCodec;
import com.scouter.goalsmith.GoalSmith;
import com.scouter.goalsmith.data.predicates.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class PredicateRegistry {
    public static final DeferredRegister<MapCodec<? extends PredicateCodec<?>>> PREDICATE_SERIALIZER = DeferredRegister.create(PMRegistries.Keys.PREDICATE_TYPE_SERIALIZERS, GoalSmith.MODID);

    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<? extends OrPredicate<?>>> OR_PREDICATE = PREDICATE_SERIALIZER.register("or", () -> OrPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<? extends AndPredicate<?>>> AND_PREDICATE = PREDICATE_SERIALIZER.register("and", () -> AndPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<? extends NegatePredicate<?>>> NEGATE_PREDICATE = PREDICATE_SERIALIZER.register("negate", () -> NegatePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<? extends TruePredicate<?>>> TRUE_PREDICATE = PREDICATE_SERIALIZER.register("true", () -> TruePredicate.CODEC);


    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<EntityTypePredicate>> ENTITY_TYPE_PREDICATE = PREDICATE_SERIALIZER.register("is_entity", () -> EntityTypePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<CanBeCollidedWithPredicate>> CAN_BE_COLLIDED_WITH = PREDICATE_SERIALIZER.register("can_be_collided_with", () -> CanBeCollidedWithPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<EntityIsPassengerPredicate>> ENTITY_IS_PASSENGER = PREDICATE_SERIALIZER.register("is_passenger", () -> EntityIsPassengerPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<EntityIsVehiclePredicate>> ENTITY_IS_VEHICLE = PREDICATE_SERIALIZER.register("is_vehicle", () -> EntityIsVehiclePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<EntityStillAlivePredicate>> ENTITY_IS_ALIVE = PREDICATE_SERIALIZER.register("is_alive", () -> EntityStillAlivePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsPlayerPredicate>> IS_PLAYER = PREDICATE_SERIALIZER.register("is_player", () -> IsPlayerPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsSpectatorPredicate>> IS_SPECTATOR = PREDICATE_SERIALIZER.register("is_spectator", () -> IsSpectatorPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsCreativePredicate>> IS_CREATIVE = PREDICATE_SERIALIZER.register("is_creative", () -> IsCreativePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsFreezingPredicate>> IS_FREEZING = PREDICATE_SERIALIZER.register("is_freezing", () -> IsFreezingPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsFreezingPredicate>> IS_ON_FIRE = PREDICATE_SERIALIZER.register("is_on_fire", () -> IsOnFirePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsDayPredicate>> IS_DAY = PREDICATE_SERIALIZER.register("is_day", () -> IsDayPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsNightPredicate>> IS_NIGHT = PREDICATE_SERIALIZER.register("is_night", () -> IsNightPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsInvisiblePredicate>> IS_INVISIBLE = PREDICATE_SERIALIZER.register("is_invisible", () -> IsInvisiblePredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<LastHurtByMobIsNullPredicate>> LAST_HURT_BY_MOB_IS_NULL = PREDICATE_SERIALIZER.register("last_hurt_by_mob_is_null", () -> LastHurtByMobIsNullPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<EntityNotBeingRiddenPredicate>> ENTITY_NOT_BEING_RIDDEN = PREDICATE_SERIALIZER.register("entity_not_being_ridden", () -> EntityNotBeingRiddenPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<NoCreativeOrSpectatorPredicate>> NO_CREATIVE_OR_SPECTATOR = PREDICATE_SERIALIZER.register("no_creative_or_spectator", () -> NoCreativeOrSpectatorPredicate.CODEC);
    public static final DeferredHolder<MapCodec<? extends PredicateCodec<?>>, MapCodec<IsDifficultyPredicate>> IS_DIFFICULTY = PREDICATE_SERIALIZER.register("is_difficulty", () -> IsDifficultyPredicate.CODEC);

}
